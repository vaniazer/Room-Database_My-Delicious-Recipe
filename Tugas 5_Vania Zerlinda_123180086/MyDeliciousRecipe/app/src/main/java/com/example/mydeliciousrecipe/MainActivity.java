package com.example.mydeliciousrecipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "my_pref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";

    EditText user, pass;
    Button login;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.button_login);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String sharedUsername = sharedPreferences.getString(KEY_USERNAME,null);

        if (sharedUsername != null){
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intent);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Mendapat username, password dari EditText
                String username = user.getText().toString();
                String password = pass.getText().toString();


                // Check if username, password is filled
                if(username.trim().length() > 0 && password.trim().length() > 0){
                    if(password.equals("admin")) {
                        //Diatur bahwa passwordnya admin
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_USERNAME, user.getText().toString());
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        Toast.makeText(getApplicationContext(),"Selamat Datang " + username, Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();

                    }
                    else {
                        alert.showAlertDialog(MainActivity.this, "Tidak Berhasil Login", "Masukkan Username dan Password Anda", false);
                    }
                }else if(username.trim().length() == 0 ){
                    //JIka Username Kosong
                    alert.showAlertDialog(MainActivity.this, "Tidak Berhasil Login", "Masukkan Username dan Password Anda", false);
                }
                else if(password.trim().length() == 0 ){
                    //Jika Password Kosong
                    alert.showAlertDialog(MainActivity.this, "Tidak Berhasil Login", "Masukkan Username dan Password Anda", false);
                }
            }
        });
    }


}