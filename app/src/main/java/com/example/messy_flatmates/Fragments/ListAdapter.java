package com.example.messy_flatmates.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.Get_requests;
import com.example.messy_flatmates.db.InternalDBHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter {

    private List<String> mData;
    private LayoutInflater mInflater;
    private final Context context;
    JSONObject responseJSON;
    Integer firstId;

    public ListAdapter(Context contextIn){
        context = contextIn;
        Get_requests get_requests = new Get_requests();
        InternalDBHandler internalDBHandler = new InternalDBHandler(context);

        String token = internalDBHandler.getToken();
        JSONObject response = get_requests.Get_My_Tasks(token);

        System.out.println("here...............");


        try{
            System.out.println(response);
            System.out.println("Length of json array :  " + response.getJSONArray("array").length());

            firstId = Integer.parseInt(response.getJSONArray("array").getJSONObject(0).getString("task_id"));
            responseJSON = response;

            for(int i = 0; i <= response.getJSONArray("array").length(); i++){

                System.out.println(response.getJSONArray("array").getJSONObject(i).getString("task_name"));
                System.out.println(response.getJSONArray("array").getJSONObject(i).toString());

            }


        } catch (JSONException e){
            System.out.println("JSON EXCEPTION");

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
        try{
            return (responseJSON.getJSONArray("array").length());
        } catch (JSONException e){
            System.out.println(e.getMessage());
            return 0;
        }
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
                    System.out.println(taskBTN.getId());
                    Bundle bundle = new Bundle();

                    System.out.println("taskbtn id: " + (taskBTN.getId()));
                    try{

                        for(int i = 0; i < responseJSON.getJSONArray("array").length(); i++ ){
                            System.out.println((responseJSON.getJSONArray("array").getJSONObject(i).getString("task_id")) + taskBTN.getId());
                            if(Integer.parseInt(responseJSON.getJSONArray("array").getJSONObject(i).getString("task_id")) == (taskBTN.getId())){
                                bundle.putString("task", responseJSON.getJSONArray("array").getJSONObject(i).toString());
                                System.out.println("bundle working?");
                                break;
                            }
                        }


                    } catch(JSONException e){
                        System.out.println(e.getMessage());
                    }

                    View_taks_fragment view_taks_fragment = new View_taks_fragment();
                    view_taks_fragment.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, view_taks_fragment).addToBackStack(null).commit();

                }
            });
            //@todo add image

            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            JSONObject taskJSON;
            try{


                taskBTN.setId(Integer.parseInt(responseJSON.getJSONArray("array").getJSONObject(position).getString("task_id")));
                taskBTN.setText((responseJSON.getJSONArray("array").getJSONObject(position).getString("task_name")) + taskBTN.getId());

            } catch (JSONException e){
                System.out.println(e.getMessage());
            }


        }

        @Override
        public void onClick(View view) {

        }
    }

}