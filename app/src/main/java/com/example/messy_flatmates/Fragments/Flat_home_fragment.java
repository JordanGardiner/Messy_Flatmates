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
import com.example.messy_flatmates.db.Get_requests;
import com.example.messy_flatmates.db.InternalDBHandler;
import com.example.messy_flatmates.db.Post_requests;
import com.example.messy_flatmates.db.Put_requests;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @version 1.0
 * Responsible for displaying your current flat information or it displays the information to join a flat
 * using a flat code
 * @author Jordan Gardiner
 */
public class Flat_home_fragment extends Fragment {
   View myView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Get_requests get_requests = new Get_requests();
        final Post_requests post_requests = new Post_requests();
        final Put_requests put_requests = new Put_requests();


        InternalDBHandler internalDBHandler = new InternalDBHandler(getContext());
        final Extra_Code wrapper = new Extra_Code();

        //check if user has a flat
        final String token = internalDBHandler.getToken();
        final JSONObject response = get_requests.Get_Flat(token);

        try {
            if (response.getString("responseCode").equals("200")) {
                //Individuals flat found

                myView = inflater.inflate(R.layout.flat_home_layout, container, false);

                ConstraintLayout constraintLayout = myView.findViewById(R.id.flat_homeConstraintLayout);
                constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wrapper.hideKeyboardFrom(getContext(), myView);
                    }
                });
                Button flatDeetsBtn = myView.findViewById(R.id.flat_homeFlatDetailsBTN);
                flatDeetsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wrapper.createDialog(getContext(), "Success", "Flat details: " +
                                response.toString(), getActivity()).show();                }
                });

                Button leaveFlat = myView.findViewById(R.id.flat_homeLeaveFlatBTN);
                leaveFlat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        JSONObject response = put_requests.removeUserFromFlat(token);
                        //@todo add a check for an admin
                        try {
                            if (response.getString("responseCode").equals("201")){
                                wrapper.createDialog(getContext(), "Success!", "You have been removed from the flat", getActivity()).show();
                                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Flat_home_fragment()).commit();
                            } else if(response.getString("responseCode").equals("401")){
                                //unauthorised
                            } else if(response.getString("responseCode").equals("404")) {
                                //not found
                            } else {
                                wrapper.createDialog(getContext(), "Oops!", "Something went wrong!", getActivity()).show();
                                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Flat_home_fragment()).commit();
                            }

                        } catch (JSONException e){
                            System.out.println(e.getMessage());
                        }
                        //returns not authorised if the user is not the admin of the flat.

                    }
                });

                Button flatCode = myView.findViewById(R.id.flat_homeGetFlatToken);
                flatCode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       try {
                           JSONObject response = get_requests.Get_Flat_Token(token);
                           wrapper.createDialog(getContext(), "Invite Token", response.getString("token"), getActivity()).show();
                       } catch(JSONException e){
                           System.out.println(e.getMessage());
                       }
                    }
                });


            } else if(response.getString("responseCode").equals("401")){
                //user is not logged on
                wrapper.createDialog(getContext(), "Oops!", "Please log on before viewing this!", getActivity()).show();

            } else if(response.getString("responseCode").equals("404")) {
                //Flat does not exist
                myView = inflater.inflate(R.layout.join_or_create_flat_layout, container, false);


                ConstraintLayout constraintLayout = myView.findViewById(R.id.join_or_create_flatConstraintLayout);
                constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wrapper.hideKeyboardFrom(getContext(), myView);
                    }
                });


                Button createFlatbtn = myView.findViewById(R.id.join_or_create_flatCreateFlatBtn);
                createFlatbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Create_flat_fragment()).commit();

                    }
                });

                Button joinFlat = myView.findViewById(R.id.join_or_create_flatJoinFlatBTN);
                joinFlat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText flatCode = (myView.findViewById(R.id.join_or_create_flatFlatCodeEditText));
                        String inviteToken = flatCode.getText().toString();
                        JSONObject response = post_requests.New_flatty(inviteToken, token);
                        try {
                            if (response.getString("responseCode").equals("201")) {
                                wrapper.createDialog(getContext(), "Success!", "You have been added to the flat", getActivity()).show();
                                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Flat_home_fragment()).commit();
                            }else if(response.getString("responseCode").equals("404")){
                                wrapper.createDialog(getContext(), "Wait, that's Impossible!", "This " +
                                        "Flat does not exist, please get a new code", getActivity()).show();
                            } else {
                                wrapper.createDialog(getContext(), "Oops!", response.getString("message"), getActivity()).show();
                            }
                        } catch (JSONException e){
                            System.out.println(e.getMessage());
                        }
                    }
                });


            }

        } catch (JSONException e){
            System.out.println(e.getMessage());
        }

        return myView;
    }

}
