package com.example.messy_flatmates.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.messy_flatmates.Extra_Code;
import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.Get_requests;
import com.example.messy_flatmates.db.InternalDBHandler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @version 1.0
 * responsible for displaying and editing a users profile.
 * @author Jordan Gardiner
 */
public class My_profile extends Fragment{

    View myView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.my_profile_layout, container, false);

        final Extra_Code wrapper = new Extra_Code();
        final Get_requests get_requests = new Get_requests();
        InternalDBHandler internalDBHandler = new InternalDBHandler(getContext());

        final String token = internalDBHandler.getToken();

        JSONObject user = get_requests.Get_user(token, null);

        TextView txt_points = myView.findViewById(R.id.txt_points);
        TextView txt_dob = myView.findViewById(R.id.txt_dob);
        TextView txt_email = myView.findViewById(R.id.txt_email);
        TextView txt_status = myView.findViewById(R.id.txt_status);
        TextView txt_name = myView.findViewById(R.id.txt_name);


        try {

            txt_points.setText(user.getString("points"));
            txt_name.setText(user.getString("first_name"));
            txt_email.setText(user.getString("email"));

            if(user.getString("status") == "null"){
                txt_status.setText("NA");
            } else {
                txt_status.setText(user.getString("status"));
            }

            if(user.getString("dob") == null){
                txt_dob.setText("NA");
            } else {
                txt_dob.setText(user.getString("dob"));
            }



        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return myView;
    }

}
