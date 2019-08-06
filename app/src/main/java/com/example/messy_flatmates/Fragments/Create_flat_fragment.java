package com.example.messy_flatmates.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

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

import org.json.JSONException;
import org.json.JSONObject;

public class Create_flat_fragment extends Fragment {


    View myView;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.create_flat_layout, container, false);

        final InternalDBHandler internalDBHandler = new InternalDBHandler(getContext());
        final Extra_Code wrapper = new Extra_Code();
        final Post_requests post_requests = new Post_requests();
        final String token = internalDBHandler.getToken();

        Button createFlat = myView.findViewById(R.id.create_flat_layoutCreateBtn);
        createFlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(token == null){
                    (wrapper.createDialog(getContext(), "Oops!", "Please log in before creating a flat")).show();
                    (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Login_Home_page()).commit();
                }
                EditText addr = myView.findViewById(R.id.create_flatAddressEditText);
                JSONObject response = post_requests.Create_flat(addr.getText().toString(), token);
                try {
                    if (response.getString("responseCode").equals("201")) {
                        (wrapper.createDialog(getContext(),"Success!", "Your flat was created!")).show();
                        (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Calendar_fragment()).commit();

                    } else if(response.getString("responseCode").equals("400")){
                        (wrapper.createDialog(getContext(),"Oops!", "You're already assigned to a flat, to" +
                                "please leave a flat before creating a new one")).show();
                    } else {
                        (wrapper.createDialog(getContext(),"Oops!", "Something went wrong!")).show();
                    }
                } catch (JSONException e){
                    System.out.println(e.getMessage());
                }
            }
        });


        return myView;
    }

}
