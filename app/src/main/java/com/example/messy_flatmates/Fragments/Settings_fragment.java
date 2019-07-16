package com.example.messy_flatmates.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.Post_requests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Settings_fragment extends Fragment {
    View myView;
    //    Dialog myDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.settings_layout, container, false);
        //     myDialog = new Dialog(getContext());

        TextView test_string = myView.findViewById(R.id.test_string_textView);
        test_string.setText("hello world!!");

        Button dev_mode = myView.findViewById(R.id.dev_login_btn);
        dev_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Create User");
                builder.setMessage("Fill in form to create a user");
                builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setCancelable(true);

                final View popup;

                popup = inflater.inflate(R.layout.create_user_layout, null);

                builder.setView(popup);
                builder.show();



                final EditText dateBox = popup.findViewById(R.id.date_editText);
                dateBox.addTextChangedListener(new TextWatcher() {
                    int prevL;
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        prevL = dateBox.getText().toString().length();

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        int length = editable.length();
                        if ((prevL < length) && (length == 2 || length == 5)) {
                            editable.append("-");
                        }
                    }
                });

                final Button create_userBtn = (Button) popup.findViewById(R.id.create_user_btn);
                create_userBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Post_requests create_userREQ = new Post_requests();

                        final EditText fname = popup.findViewById(R.id.fName_editText);
                        EditText sname = popup.findViewById(R.id.lName_editText);
                        EditText email = popup.findViewById(R.id.email_editText5);
                        EditText password = popup.findViewById(R.id.password_editText);
                        EditText dob = popup.findViewById(R.id.date_editText);

                        System.out.println(fname.getText());
                        System.out.println(sname.getText());
                        System.out.println(email.getText());
                        String responce = create_userREQ.Create_user(fname.getText().toString(), sname.getText().toString(), email.getText().toString(), password.getText().toString(), dob.getText().toString());
                        System.out.println(responce);
                        if (responce == "OK"){
                            System.out.println("It worked!");
                        } else {
                            System.out.println("unlucky !");
                        }
                    }
                });


            }
        });

        return myView;
    }


}
