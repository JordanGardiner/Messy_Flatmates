package com.example.messy_flatmates.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messy_flatmates.R;

import java.util.ArrayList;

/**
 * @version 1.0
 * Responsible for displaying a task
 * @author Jordan Gardiner
 */
public class My_task_fragment extends Fragment {
    View myView;
    ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.my_task_layout, container, false);





        RecyclerView recyclerView = myView.findViewById(R.id.myTaskRecyclerView);

        ListAdapter listAdapter = new ListAdapter(getContext());
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        return myView;
    }
}
