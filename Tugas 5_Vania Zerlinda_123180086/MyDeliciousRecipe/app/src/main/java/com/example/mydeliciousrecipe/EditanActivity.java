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

public class EditanActivity extends AppCompatActivity {

    public static final String EXTRA_NOTE = "extra_note";
    public static final int REQUEST_EDIT = 200;
    public static final int RESULT_EDIT = 210;

    private EditText Edit_Nama, Edit_Lama, Edit_Orang, Edit_Bahan, Edit_Cara;
    Button btnEditSimpan, btnEditKembali;
    private Resep resep;
    private ResepDao resepDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editan);

        resepDao = ResepDatabase.getInstance(this).resepDao();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Edit");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        resep = getIntent().getParcelableExtra(EXTRA_NOTE);

        Edit_Nama = findViewById(R.id.edit_nama);
        Edit_Lama = findViewById(R.id.edit_lama);
        Edit_Orang = findViewById(R.id.edit_porsi);
        Edit_Bahan = findViewById(R.id.edit_bahan);
        Edit_Cara = findViewById(R.id.edit_langkah);
        btnEditKembali = findViewById(R.id.btn_edit_kembali);
        btnEditSimpan = findViewById(R.id.btn_edit_simpan);

        Edit_Nama.setText(resep.getNama());
        Edit_Lama.setText(resep.getLama());
        Edit_Orang.setText(resep.getOrang());
        Edit_Bahan.setText(resep.getBahan());
        Edit_Cara.setText(resep.getCara());

        btnEditSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = Edit_Nama.getText().toString();
                String lama = Edit_Lama.getText().toString();
                String orang = Edit_Orang.getText().toString();
                String bahan = Edit_Bahan.getText().toString();
                String cara = Edit_Cara.getText().toString();

                Resep updateResep = new Resep(resep.getId(), nama, lama, orang, bahan, cara);

                resepDao.updateData(updateResep);

                setResult(RESULT_EDIT);
                finish();

                Intent intent = new Intent(EditanActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        btnEditKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditanActivity.this, DetailActivity.class);
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
        message = "Apakah ingin membatalkan edit resep?";

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