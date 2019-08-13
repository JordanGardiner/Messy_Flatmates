package com.example.messy_flatmates.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.messy_flatmates.R;

/**
 * @versoin 1.0
 *
 */
public class dashboard_fragment extends Fragment {

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.dashboard_layout, container, false);



        return myView;
    }

}
