package com.example.messy_flatmates.Fragments;

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
import com.example.messy_flatmates.db.InternalDBHandler;
import com.example.messy_flatmates.db.Post_requests;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * @version 1.0
 * responsible for logging a user in and storing their session token in the sqlite database
 * @TODO add the rest of the error messages that correspond to the server requests.
 * @TODO sort out time delay from request to server
 * @author Jordan Gardiner
 * */
public class User_login_fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View myView;
        final Extra_Code wrapper = new Extra_Code();
        myView = inflater.inflate(R.layout.user_login_layout, container, false);
        final InternalDBHandler dbHandler = new InternalDBHandler(getContext());


        Button loginBtn = myView.findViewById(R.id.login_homeLoginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrapper.hideKeyboardFrom(getContext(), myView);
                Post_requests post_requests = new Post_requests();
                EditText email = myView.findViewById(R.id.user_loginEmailEditTextLogin);
                EditText password = myView.findViewById(R.id.user_loginPasswordEditTextLogin);
                JSONObject response = post_requests.Login(email.getText().toString(), password.getText().toString());

                try {
                    if ((response.getString("responseCode")).equals("200")) {

                        System.out.println("Attempting to add token to internal db");
                        if (dbHandler.addSession(response.getString("id"), response.getString("token")) == true){
                            wrapper.createDialog(getContext(), "HURRAH!", (response.getString
                                    ("id") + "\n" + dbHandler.getToken()), (getActivity())).show();
                            (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Calendar_fragment()).commit();

                        } else {
                            wrapper.createDialog(getContext(), "Oops! something went wrong", (response.getString
                                    ("id") + "\n" + dbHandler.getToken()), (getActivity())).show();

                        }

                    } else {
                        wrapper.createDialog(getContext(), "Oops!", (response.getString
                                ("message")), (getActivity())).show();

                    }
                } catch(JSONException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        ConstraintLayout constraintLayout = myView.findViewById(R.id.user_loginConstraintLayout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrapper.hideKeyboardFrom(getContext(), myView);
            }
        });

        return myView;

    }

}
