package com.example.mydeliciousrecipe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etName, etUsername, etEmail, etPhone;
    Button btnSimpan, btnKembali;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "my_pref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etName = findViewById(R.id.et_nama);
        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnKembali = findViewById(R.id.btn_kembali);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String Nama = sharedPreferences.getString(KEY_NAME,null);
        String Username = sharedPreferences.getString(KEY_USERNAME,null);
        String Email = sharedPreferences.getString(KEY_EMAIL,null);
        String Phone = sharedPreferences.getString(KEY_PHONE,null);

        if (Nama != null || Username != null || Email != null || Phone != null){

            etName.setText(Nama);
            etUsername.setText(Username);
            etEmail.setText(Email);
            etPhone.setText(Phone);
        }

        btnSimpan.setOnClickListener(this);
        btnKembali.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_simpan:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME,etName.getText().toString());
                editor.putString(KEY_USERNAME,etUsername.getText().toString());
                editor.putString(KEY_EMAIL,etEmail.getText().toString());
                editor.putString(KEY_PHONE,etPhone.getText().toString());
                editor.apply();
                finish();
                break;

            case R.id.btn_kembali:
                finish();
                break;
        }

    }
}