package com.example.messy_flatmates.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.messy_flatmates.R;


public class User_login_fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View myView;


        myView = inflater.inflate(R.layout.user_login_layout, container, false);
        return myView;

    }

}
