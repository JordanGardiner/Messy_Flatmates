package com.example.messy_flatmates.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.this_is_a_test.R;


public class Settings_fragment extends Fragment {
    View myView;
    //    Dialog myDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.settings_layout, container, false);
        //     myDialog = new Dialog(getContext());

        TextView test_string = (TextView) myView.findViewById(R.id.test_string_textView);
        test_string.setText("hello world!!");

        Button dev_mode = (Button) myView.findViewById(R.id.dev_login_btn);
        dev_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("login");
                builder.setMessage("Insert password to gain dev access");
                builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setCancelable(true);

                View popup;
                popup = inflater.inflate(R.layout.dev_login_layout, null);

                builder.setView(popup);
                builder.show();

            }
        });

        return myView;
    }
}
