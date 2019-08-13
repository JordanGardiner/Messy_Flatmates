package com.example.messy_flatmates.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.messy_flatmates.Extra_Code;
import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.InternalDBHandler;

/**
 * @version 1.0
 * The calendar fragment is responsible for holding the calendar where users can select tasks to view details
 * Currently it just has a logout button for testing.
 * @author Jordan Gardiner
 */
public class Calendar_fragment extends Fragment {

    private View myView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.calendar_layout, container, false);

        return myView;
    }
}
