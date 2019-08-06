package com.example.messy_flatmates.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.messy_flatmates.Extra_Code;
import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.Post_requests;

import org.json.JSONException;
import org.json.JSONObject;

public class Create_user_fragment extends Fragment {

    Extra_Code wrapper = new Extra_Code();

    /**
     * TODO: 23/07/2019 Create checks to stop DOB going over day and month limits
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View myView;
        myView = inflater.inflate(R.layout.create_user_layout, container, false);

        final EditText dateBox = myView.findViewById(R.id.create_userDate_editText);
        wrapper.dateFormat(dateBox);


        final Button create_userBtn = myView.findViewById(R.id.create_userCreate_btn);
        create_userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post_requests create_userREQ = new Post_requests();

                final EditText fname = myView.findViewById(R.id.create_userFName_editText);
                EditText sname = myView.findViewById(R.id.create_userLName_editText);
                EditText email = myView.findViewById(R.id.create_userEmail_editText5);
                EditText password = myView.findViewById(R.id.create_userPassword_editText);
                EditText dob = myView.findViewById(R.id.create_userDate_editText);

                // format the date so the DB accepts it yyyy-mm-dd

                String originalText = dob.getText().toString();
                String finalDOB = wrapper.parseDate(originalText);

                System.out.println(fname.getText());
                System.out.println(sname.getText());
                System.out.println(email.getText());
                JSONObject response = create_userREQ.Create_user(fname.getText().toString(), sname.getText().toString(), email.getText().toString(), password.getText().toString(), finalDOB);
                //System.out.println(response.getJSONObject("responseBody").toString());
                System.out.println("testing json object");
                try{
                    String resCode = response.getString("responseCode");
                    String user_id = response.getString("id");
                    if (resCode.equals("201")) {
                        AlertDialog.Builder builder = wrapper.createDialog(getContext(), "Success!", "An account has been created");
                        System.out.println("It worked!");
                        System.out.println(user_id);
                        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Calendar_fragment calendar_fragment = new Calendar_fragment();
                                        (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, calendar_fragment).commit();

                                    }
                                });
                        builder.show();

                    } else if(resCode.equals("408")) {
                        (wrapper.createDialog(getContext(), "408", "Connection error, " +
                                "please check you are connected to the internet and retry")).show();

                        System.out.println("unlucky !");
                    } else if(resCode.equals("409")){
                        (wrapper.createDialog(getContext(), resCode, user_id)).show();
                        myView.findViewById(R.id.create_userEmail_editText5).setFocusable(true);

                        System.out.println("unlucky ! user already exists");
                    } else {
                        System.out.println("IDFK");
                    }

                    System.out.println(resCode);

                } catch (JSONException e){
                    System.out.println("or here ??");
                    System.out.println(e.getMessage());
                }

            }
        });

        ConstraintLayout constraintLayout = myView.findViewById(R.id.create_user_constraint_layout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrapper.hideKeyboardFrom(getContext(), myView);
            }
        });


        return myView;
    }




}
