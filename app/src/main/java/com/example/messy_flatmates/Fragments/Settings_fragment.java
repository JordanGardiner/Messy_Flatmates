package com.example.messy_flatmates.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.messy_flatmates.Extra_Code;
import com.example.messy_flatmates.MainActivity;
import com.example.messy_flatmates.R;
import com.example.messy_flatmates.db.InternalDBHandler;

/**
 * @version 1.0
 */
public class Settings_fragment extends Fragment {
    View myView;
    //    Dialog myDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.settings_layout, container, false);

        final InternalDBHandler dbhandler = new InternalDBHandler(getContext());
        final Extra_Code wrapper = new Extra_Code();

        Button logout = myView.findViewById(R.id.settings_LogoutBTN);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbhandler.removeSession() == true){
                    wrapper.createDialog(getContext(), "Success", "You have been logged out", (getActivity())).show();
                    (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Login_Home_page()).commit();
                } else {
                    wrapper.createDialog(getContext(), "Oops", "Something went wrong and you haven't been logged out!", (getActivity())).show();
                }
            }
        });

        return myView;
    }




}
