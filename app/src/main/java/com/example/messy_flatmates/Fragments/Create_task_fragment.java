package com.example.messy_flatmates.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.messy_flatmates.Extra_Code;
import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.InternalDBHandler;
import com.example.messy_flatmates.db.Post_requests;

import org.json.JSONException;
import org.json.JSONObject;


public class Create_task_fragment extends Fragment {

    public Create_task_fragment() {
        // Required empty public constructor
    }

    View myView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.create_task_layout, container, false);
        final Extra_Code wrapper = new Extra_Code();
        final Post_requests post_requests = new Post_requests();
        final InternalDBHandler internalDBHandler = new InternalDBHandler(getContext());


        EditText startDate = myView.findViewById(R.id.create_taskDueDateEditText2);
        wrapper.dateFormat(startDate);


        Button createTaskbtn = myView.findViewById(R.id.create_taskCreateBtn);
        createTaskbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrapper.hideKeyboardFrom(getContext(), myView);

                EditText taskName = myView.findViewById(R.id.create_taskTitleEditText);
                EditText taskDescription = myView.findViewById(R.id.create_taskDescriptoinEditText);
                EditText taskDueDate = myView.findViewById(R.id.create_taskDueDateEditText2);
                EditText taskPoints = myView.findViewById(R.id.create_taskPointsEditText);

                String parsedDueDate = wrapper.parseDate(taskDueDate.getText().toString());

                String parsedPoints = taskPoints.getText().toString(); //@todo maybe create a wrapper to control point values?

                String token = internalDBHandler.getToken();
                System.out.println("Printing token in create task");
                System.out.println(token);
                if(token == null){
                    wrapper.createDialog(getContext(), "Oops!", "Please log in before creating a task", (getActivity())).show();
                    (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Login_Home_page()).commit();
                }

                JSONObject response = post_requests.Create_task(taskName.getText().toString(),
                        taskDescription.getText().toString(), parsedDueDate, parsedPoints, token);
                try {
                    if (response.getString("responseCode").equals("201")) {
                        wrapper.createDialog(getContext(), "Success!", "Task has been created", (getActivity())).show();
                        (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Calendar_fragment()).commit();

                    } else if(response.getString("responseCode").equals("404")){
                        wrapper.createDialog(getContext(), "Woah! Easy tiger!", "Please Join or create a flat in settings " +
                                "before creating tasks", (getActivity())).show();
                    } else {
                        wrapper.createDialog(getContext(), "Oops!", "Something went wrong! Please try again", (getActivity())).show();
                    }
                } catch (JSONException e){
                    System.out.println("Error in the json of create task");
                    System.out.println(e.getMessage());
                }

            }
        });



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
