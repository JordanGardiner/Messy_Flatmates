package com.example.messy_flatmates.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.messy_flatmates.MainActivity;

public class Extra_Code {

    public AlertDialog.Builder createDialog(Context context, String title, String message){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.create();
        return builder;
    }
}
