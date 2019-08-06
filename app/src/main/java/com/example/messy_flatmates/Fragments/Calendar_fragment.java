package com.example.messy_flatmates.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.messy_flatmates.Extra_Code;
import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.InternalDBHandler;

public class Calendar_fragment extends Fragment {

    private View myView;

    public Calendar_fragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.calendar_layout, container, false);
        final InternalDBHandler dbhandler = new InternalDBHandler(getContext());


        Button logout = myView.findViewById(R.id.calendarLogoutBtn);
        final Extra_Code wrapper = new Extra_Code();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbhandler.removeSession() == true){
                    wrapper.createDialog(getContext(), "Success", "You have been logged out").show();
                    (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Login_Home_page()).commit();

                } else {
                    wrapper.createDialog(getContext(), "Oops", "Something went wrong and you haven't been logged out!").show();
                }
            }
        });


        return myView;
    }
}
