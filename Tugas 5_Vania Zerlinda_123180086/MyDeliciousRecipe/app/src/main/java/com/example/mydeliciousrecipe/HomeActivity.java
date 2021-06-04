package com.example.mydeliciousrecipe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class HomeActivity extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_home, container, false);
    }
 
    AlertDialogManager alert = new AlertDialogManager();

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "my_pref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";


    // Button Logout
    Button btnLogout, btnEdit;
    TextView Nama,Name,Email,Phone;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Nama = view.findViewById(R.id.lblNama);
        Name = view.findViewById(R.id.lblName);
        Email = view.findViewById(R.id.lblEmail);
        Phone = view.findViewById(R.id.lblPhone);

        // Button logout dan edit
        btnLogout = view.findViewById(R.id.buttonLogOut);
        btnEdit = view.findViewById(R.id.btn_edit);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        ProsesProfil();

        btnEdit.setOnClickListener(this);
        btnLogout.setOnClickListener(this);


    }

    @Override
    public void onResume() {
        super.onResume();
        ProsesProfil();
    }

    @SuppressLint("SetTextI18n")
    private void ProsesProfil(){
        String Namaku = sharedPreferences.getString(KEY_NAME,"Tidak Ada Data");
        String User_name = sharedPreferences.getString(KEY_USERNAME,"Tidak Ada Data");
        String Emailku = sharedPreferences.getString(KEY_EMAIL,"Tidak Ada Data");
        String Phoneku = sharedPreferences.getString(KEY_PHONE,"Tidak Ada Data");

        Nama.setText(": " + Namaku);
        Name.setText(User_name);
        Email.setText(": " + Emailku);
        Phone.setText(": " + Phoneku);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_edit:
                Intent intent = new Intent(getContext(), EditActivity.class);
                startActivity(intent);
                break;

            case R.id.buttonLogOut:
                Toast.makeText(getContext(),"Selamat Tinggal " + sharedPreferences.getString(KEY_USERNAME,"None"), Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(getContext(), MainActivity.class);
                Objects.requireNonNull(getActivity()).finish();
                Objects.requireNonNull(getContext()).startActivity(i);
                break;
        }
    }
}