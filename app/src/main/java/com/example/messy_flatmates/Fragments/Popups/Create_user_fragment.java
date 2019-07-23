package com.example.messy_flatmates.Fragments.Popups;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.Post_requests;

import org.json.JSONException;
import org.json.JSONObject;

public class Create_user_fragment extends Fragment {

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

        final EditText dateBox = myView.findViewById(R.id.date_editText);
        dateBox.addTextChangedListener(new TextWatcher() {
            int prevL;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                prevL = dateBox.getText().toString().length();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.length();
                if ((prevL < length) && (length == 2 || length == 5)) {
                    editable.append("-");
                }
            }
        });


        final Button create_userBtn = myView.findViewById(R.id.create_user_btn);
        create_userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post_requests create_userREQ = new Post_requests();

                final EditText fname = myView.findViewById(R.id.fName_editText);
                EditText sname = myView.findViewById(R.id.lName_editText);
                EditText email = myView.findViewById(R.id.email_editText5);
                EditText password = myView.findViewById(R.id.password_editText);
                EditText dob = myView.findViewById(R.id.date_editText);

                // format the date so the DB accepts it yyyy-mm-dd

                String originalText = dob.getText().toString();
                String delims = "[-]";
                String[] arrayOfDate = originalText.split(delims);
                String finalDOB = arrayOfDate[2] + arrayOfDate[1] + arrayOfDate[0];



                System.out.println(fname.getText());
                System.out.println(sname.getText());
                System.out.println(email.getText());
                JSONObject response = create_userREQ.Create_user(fname.getText().toString(), sname.getText().toString(), email.getText().toString(), password.getText().toString(), finalDOB);
                //System.out.println(response.getJSONObject("responseBody").toString());
                System.out.println("testing json object");
                try{
                    String resCode = response.get("responseCode").toString();
                    if (resCode.equals("201")) {
                        System.out.println("It worked!");
                    } else {
                        System.out.println("unlucky !");
                    }
                    System.out.println(resCode);

                } catch (JSONException e){
                    System.out.println("or here ??");
                    System.out.println(e.getMessage());
                }

            }
        });

        ConstraintLayout constraintLayout = myView.findViewById(R.id.constraintLayout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboardFrom(getContext(), myView);
            }
        });


        return myView;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
