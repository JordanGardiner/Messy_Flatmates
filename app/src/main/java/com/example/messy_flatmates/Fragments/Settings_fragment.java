package com.example.messy_flatmates.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.messy_flatmates.MainActivity;
import com.example.messy_flatmates.R;

/**
 * @version 1.0
 */
public class Settings_fragment extends Fragment {
    View myView;
    //    Dialog myDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.settings_layout, container, false);

        return myView;
    }




}
