package com.example.messy_flatmates.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.messy_flatmates.R;

import org.json.JSONException;
import org.json.JSONObject;

public class View_taks_fragment extends Fragment {

    JSONObject task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView =inflater.inflate(R.layout.view_tak_layout, container, false);
        Bundle bundle = this.getArguments();
        JSONObject taskJSON;


        try{
            taskJSON = new JSONObject(bundle.getString("task"));
            System.out.println(taskJSON.toString());

            EditText task_id = myView.findViewById(R.id.ViewTask_task_idEditText);
            EditText title = myView.findViewById(R.id.ViewTask_Task_titleEditText);
            EditText description = myView.findViewById(R.id.ViewTask_TaskDescriptionEditText);
            EditText points = myView.findViewById(R.id.ViewTask_Task_pointsEditText);
            EditText due_date = myView.findViewById(R.id.ViewTask_DueDateEditText);
            EditText completed_date = myView.findViewById(R.id.ViewTask_CompleteDateEditText);
            EditText status = myView.findViewById(R.id.ViewTask_Task_StatusEditText);


            task_id.setText(task.getString("task_id"));
            title.setText(task.getString("task_name"));
            description.setText(task.getString("description"));
            points.setText(task.getString("task_points"));
            due_date.setText(task.getString("due_date"));
            completed_date.setText(task.getString("Completed_date"));
            status.setText(task.getString("status"));

        } catch (JSONException e){
            System.out.println(e.getMessage());
        }



        return myView;
    }
}
