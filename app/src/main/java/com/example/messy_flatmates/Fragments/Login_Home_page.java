package com.example.messy_flatmates.Fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.messy_flatmates.Extra_Code;
import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.Connections;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @version 1.0
 * Responsible for redirecting to the login and create a user fragments
 * @author Jordan Gardiner
 */
public class Login_Home_page extends Fragment {

    private View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.login_home_page_layout, container, false);
        final Extra_Code wrapper = new Extra_Code();
        Connections connect = new Connections();

        Button login = myView.findViewById(R.id.login_homeLoginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User_login_fragment user_login_fragment = new User_login_fragment();
                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, user_login_fragment).commit();

            }
        });

        Button createUser = myView.findViewById(R.id.login_homeCreateUserBtn);
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Create_user_fragment create_user_fragment = new Create_user_fragment();
                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, create_user_fragment).commit();

            }
        });

        JSONObject response = connect.SendRequest( "/api/Status", null, null, "GET"); //@Todo add this to the get_request class

        TextView statusBox = myView.findViewById(R.id.login_homeDbStatus);
        try {
            System.out.println(response);
            statusBox.setText(response.getString("message"));
        }catch(JSONException e){
            System.out.println(e.getMessage());
        }


        ConstraintLayout constraintLayout = myView.findViewById(R.id.login_homeConstraintLayout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrapper.hideKeyboardFrom(getContext(), myView);
            }
        });

        return myView;
    }

}
