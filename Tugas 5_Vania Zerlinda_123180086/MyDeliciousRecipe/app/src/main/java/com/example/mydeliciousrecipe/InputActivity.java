package com.example.mydeliciousrecipe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydeliciousrecipe.database.ResepDao;
import com.example.mydeliciousrecipe.database.ResepDatabase;

public class InputActivity extends AppCompatActivity {
    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 110;
    public static final String EXTRA_ADD = "extra_add";
    private EditText input_nama, input_lama, input_porsi, input_bahan, input_cara;
    private Button btnInputSave, btnInputKembali;
    private ResepDao resepDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Save");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        resepDao = ResepDatabase.getInstance(this).resepDao();

        input_nama = findViewById(R.id.input_nama);
        input_lama = findViewById(R.id.input_lama);
        input_porsi = findViewById(R.id.input_porsi);
        input_bahan = findViewById(R.id.input_bahan);
        input_cara = findViewById(R.id.input_langkah);
        btnInputSave = findViewById(R.id.btn_input_simpan);
        btnInputKembali = findViewById(R.id.btn_input_kembali);
        btnInputSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = input_nama.getText().toString();
                String lama = input_lama.getText().toString();
                String orang = input_porsi.getText().toString();
                String bahan = input_bahan.getText().toString();
                String cara = input_cara.getText().toString();

                Resep resep = new Resep(nama, lama, orang, bahan, cara);
                resepDao.insertData(resep);

                setResult(RESULT_ADD);
                finish();
            }
        });
        btnInputKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputActivity.this, MenuFragment.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            showDialogMessage();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showDialogMessage();
    }

    private void showDialogMessage() {
        String message;
        String title;

        title = "Batal";
        message = "Apakah ingin membatalkan resep?";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create()
                .show();
    }

}