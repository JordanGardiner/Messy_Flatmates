package com.example.messy_flatmates.Fragments;

import android.content.Context;
import android.content.SyncAdapterType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messy_flatmates.MainActivity;
import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.Get_requests;
import com.example.messy_flatmates.db.InternalDBHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter {

    private List<String> mData;
    private LayoutInflater mInflater;
    private final Context context;

    public ListAdapter(Context contextIn){
        context = contextIn;
        Get_requests get_requests = new Get_requests();
        InternalDBHandler internalDBHandler = new InternalDBHandler(context);

        String token = internalDBHandler.getToken();
        JSONObject response = get_requests.Get_My_Tasks(token);
        ArrayList<String> taskTitleArray = new ArrayList<>();

        System.out.println(response);
        System.out.println("here...............");

        try{
            JSONArray responseArray = new JSONArray("{ " + response.toString() + " }");

            for(int i = 0; i <= responseArray.length(); i++){

                taskTitleArray.add(responseArray.getJSONObject(i).getString("task_name"));
                System.out.println(responseArray.getJSONObject(i).getString("task_name"));

            }


        } catch (JSONException e){
            System.out.println("here");

            System.out.println(e.getMessage());
        }




        System.out.println("this is executed how many times ??");


    }

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