package com.example.messy_flatmates.Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messy_flatmates.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter {

    private List<String> mData;
    private LayoutInflater mInflater;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_for_resourse_my_tasks, parent, false);
        return new ListViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return tempData.title.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button taskBTN;
        ImageView taskImage;


        ListViewHolder(View itemView) {
            super(itemView);
            taskBTN = itemView.findViewById(R.id.task_resourseBTN);
            taskBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("wow");

                }
            });
            //@todo add image

            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            taskBTN.setText(tempData.title[position]);

        }

        @Override
        public void onClick(View view) {

        }
    }

}