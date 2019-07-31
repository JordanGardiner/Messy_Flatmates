package com.example.messy_flatmates.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.InternalDBHandler;
import com.example.messy_flatmates.db.Post_requests;

import org.json.JSONException;
import org.json.JSONObject;


public class User_login_fragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View myView;
        myView = inflater.inflate(R.layout.user_login_layout, container, false);
        final InternalDBHandler dbHandler = new InternalDBHandler(getContext());


        Button loginBtn = myView.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post_requests post_requests = new Post_requests();

                EditText email = myView.findViewById(R.id.emailEditTextLogin);
                EditText password = myView.findViewById(R.id.passwordEditTextLogin);
                System.out.println("what about here?");
                JSONObject response = post_requests.Login(email.getText().toString(), password.getText().toString());

                try {
                    System.out.println("here???");
                    if ((response.getString("responseCode")).equals("200")) {
                        Extra_Code wrapper = new Extra_Code();

                        System.out.println("Attempting to add token to internal db");
                        dbHandler.addSession(response.getString("id"), response.getString("token"));


                        wrapper.createDialog(getContext(), "HURRAH!", (response.getString
                                ("id") + "\n" + dbHandler.getToken())).show();
                    }
                } catch(JSONException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        return myView;

    }

}
