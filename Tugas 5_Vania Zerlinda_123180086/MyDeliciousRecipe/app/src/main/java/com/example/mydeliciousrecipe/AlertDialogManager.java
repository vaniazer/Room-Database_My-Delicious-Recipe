package com.example.mydeliciousrecipe;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogManager {
    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Pengaturan Dialog Title
        alertDialog.setTitle(title);

        // Pengaturan Dialog Message
        alertDialog.setMessage(message);

        if(status != null)
            // Pengaturan alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Pengaturan OK Button
        alertDialog.setButton(Dialog.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        // Tampil Alert Message
        alertDialog.show();
    }
}
