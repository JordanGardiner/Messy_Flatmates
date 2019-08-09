package com.example.messy_flatmates.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.messy_flatmates.Extra_Code;
import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.Get_requests;
import com.example.messy_flatmates.db.InternalDBHandler;

import org.json.JSONObject;

/**
 * @version 1.0
 * responsible for displaying and editing a users profile.
 * @author Jordan Gardiner
 */
public class My_profile extends Fragment {

    View myView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.my_profile_layout, container, false);

        final Extra_Code wrapper = new Extra_Code();
        final Get_requests get_requests = new Get_requests();
        InternalDBHandler internalDBHandler = new InternalDBHandler(getContext());

        final String token = internalDBHandler.getToken();

        Button getUserBtn = myView.findViewById(R.id.my_profileGetDetailsBtn);
        getUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(token == null){
                    wrapper.createDialog(getContext(), "Oops!", "Token unavailable", (getActivity()));
                }
                JSONObject response = get_requests.Get_user(token, null);
                wrapper.createDialog(getContext(), "Response body", response.toString(), (getActivity())).show();
            }
        });



        return myView;
    }

}
