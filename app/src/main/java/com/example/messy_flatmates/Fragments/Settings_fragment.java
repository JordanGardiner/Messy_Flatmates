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

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.messy_flatmates.Fragments.Popups.Create_user_fragment;
import com.example.messy_flatmates.MainActivity;
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

        final Bundle bundle = this.getArguments();

        TextView test_string = myView.findViewById(R.id.test_string_textView);
        test_string.setText("hello world!!");

        Button dev_mode = myView.findViewById(R.id.dev_login_btn);
        dev_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Create_user_fragment create_user_fragment = new Create_user_fragment();
                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, create_user_fragment).commit();


            }
        });

        return myView;
    }


}
