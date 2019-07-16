package com.example.messy_flatmates.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.Connections;

public class Calendar_fragment extends Fragment {

    private View myView;

    public Calendar_fragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.calendar_layout, container, false);
        //Bundle bundle = this.getArguments();

        Connections connect = new Connections();

        String responce = connect.SendGetRequest( "/");


        TextView statusBox = myView.findViewById(R.id.DbStatus);
        statusBox.setText(responce);

        return myView;
    }
}
