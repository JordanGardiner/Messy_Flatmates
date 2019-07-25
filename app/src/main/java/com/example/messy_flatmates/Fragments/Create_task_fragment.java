package com.example.messy_flatmates.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.messy_flatmates.R;


public class Create_task_fragment extends Fragment {

    public Create_task_fragment() {
        // Required empty public constructor
    }

    View myView;
    Extra_Code wrapper = new Extra_Code();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.create_task_layout, container, false);





        ConstraintLayout constraintLayout = myView.findViewById(R.id.create_task_constraint_layout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrapper.hideKeyboardFrom(getContext(), myView);
            }
        });


        return myView;


    }
}
