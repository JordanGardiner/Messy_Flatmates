package com.example.messy_flatmates;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;

import com.example.messy_flatmates.Fragments.Calendar_fragment;
import com.example.messy_flatmates.MainActivity;

/**
 * @version 1.0
 * A wrapper class that is full of functions that are used by many other classes.
 * @author Jordan Gardiner
 */
public class Extra_Code {
    /**
     * Creates an alertDialog that can be displayed when called.
     * @param context (use function 'getContext()' to get the context)
     * @param title (String title)
     * @param message (String body)
     * @param activity (use function 'getActivity()')
     * @return an alertdialog, place '.show()' on the returned builder to display it.
     */
    public AlertDialog.Builder createDialog(Context context, String title, String message, final FragmentActivity activity){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.create();
        return builder;
    }

    /**
     * function that hides the keyboard when a user clicks on the screen away from the text box. The
     * layout must be set to clickable false and focus on touch false
     * @param context (getContext()
     * @param view the current view
     */
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * takes an edittext box and formats it so it adds '-' when a user types a date
     * e.g 12-12-1999
     * @param dateBox the edit text box to have the constraints
     */
    public void dateFormat(final EditText dateBox) {

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

    }

    /**
     * takes a string in form of a date (dd-mm-yyyy) and converts it to the servers standards
     * @param originalText
     * @return date in the format of yyyy-mm-dd
     */
    public String parseDate(String originalText){

        String delims = "[-]";
        String[] arrayOfDate = originalText.split(delims);
        return arrayOfDate[2] + arrayOfDate[1] + arrayOfDate[0];

    }
}
