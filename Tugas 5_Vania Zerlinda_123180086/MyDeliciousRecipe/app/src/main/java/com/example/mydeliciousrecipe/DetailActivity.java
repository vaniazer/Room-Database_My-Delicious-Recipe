package com.example.mydeliciousrecipe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydeliciousrecipe.database.ResepDao;
import com.example.mydeliciousrecipe.database.ResepDatabase;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE = "extra_note";
    public static final int REQUEST_DELETE = 200;
    public static final int RESULT_EDIT = 210;
    public static final int RESULT_DELETE = 220;
    public static final int ALERT_DIALOG_CLOSE = 10;
    public static final int ALERT_DIALOG_DELETE = 20;
    TextView tvNamaDetail, tvLamaDetail, tvOrangDetail, tvBahanDetail, tvCaraDetail;
    private Resep resep;
    private ResepDao resepDao;
    ImageView tvKembali, tvHapus, tvEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNamaDetail = findViewById(R.id.tv_detail_nama);
        tvLamaDetail = findViewById(R.id.tv_detail_lama);
        tvOrangDetail = findViewById(R.id.tv_detail_orang);
        tvBahanDetail = findViewById(R.id.tv_detail_bahan);
        tvCaraDetail = findViewById(R.id.tv_detail_cara);
        tvKembali = findViewById(R.id.back);
        tvEdit = findViewById(R.id.edit);
        tvHapus = findViewById(R.id.delete);

        resepDao = ResepDatabase.getInstance(this).resepDao();

        resep = getIntent().getParcelableExtra(EXTRA_NOTE);

        tvNamaDetail.setText(resep.getNama());
        tvLamaDetail.setText(resep.getLama());
        tvOrangDetail.setText(resep.getOrang());
        tvBahanDetail.setText(resep.getBahan());
        tvCaraDetail.setText(resep.getCara());

        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, EditanActivity.class);
                intent.putExtra(EditanActivity.EXTRA_NOTE,resep);
                startActivityForResult(intent, EditanActivity.REQUEST_EDIT);
            }
        });

        tvHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogMessage(ALERT_DIALOG_DELETE);
            }
        });

        tvKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MenuFragment.class);
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
        if (item.getItemId() == R.id.delete) {
            showDialogMessage(ALERT_DIALOG_DELETE);
        }
        return super.onOptionsItemSelected(item);
    }


    private void showDialogMessage(int type) {
        final boolean isDialogClose = (type == ALERT_DIALOG_CLOSE);
        String message;
        String title;

        if (isDialogClose) {
            title = "Kembali";
            message = "Apakah ingin kembali?";
        } else {
            title = "Delete";
            message = "Apakah ingin menghapus resep ini?";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isDialogClose) {
                            finish();
                        } else {
                            resepDao.deleteData(resep);
                            setResult(RESULT_DELETE);
                            finish();

                            Intent intent = new Intent(DetailActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
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

    @Override
    public void onBackPressed() {
        showDialogMessage(ALERT_DIALOG_CLOSE);
    }
}