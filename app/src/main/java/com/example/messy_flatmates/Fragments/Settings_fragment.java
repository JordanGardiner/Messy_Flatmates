package com.example.messy_flatmates.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.messy_flatmates.R;


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

//        Button dev_mode = myView.findViewById(R.id.dev_login_btn);
//        dev_mode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Create_user_fragment create_user_fragment = new Create_user_fragment();
//                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, create_user_fragment).commit();
//
//
//            }
//        });

        return myView;
    }


}
