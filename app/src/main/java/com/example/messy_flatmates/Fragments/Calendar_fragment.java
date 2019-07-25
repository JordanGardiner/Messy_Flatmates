package com.example.messy_flatmates.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.Connections;

import org.json.JSONException;
import org.json.JSONObject;

public class Calendar_fragment extends Fragment {

    private View myView;

    public Calendar_fragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.calendar_layout, container, false);
        //Bundle bundle = this.getArguments();

        Connections connect = new Connections();

        JSONObject response = connect.SendGetRequest( "/");




        Button login = myView.findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User_login_fragment user_login_fragment = new User_login_fragment();
                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, user_login_fragment).commit();

            }
        });

        Button createUser = myView.findViewById(R.id.createUserBtn);
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Create_user_fragment create_user_fragment = new Create_user_fragment();
                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, create_user_fragment).commit();

            }
        });

        TextView statusBox = myView.findViewById(R.id.DbStatus);
        try {
            System.out.println(response);
            statusBox.setText(response.getString("message"));
        }catch(JSONException e){
            System.out.println(e.getMessage());
        }

        return myView;
    }
}
