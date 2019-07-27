package com.example.datepicker;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {  // to create a standard date picker listener

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){ //called when dialog fragment is created
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayofMonth = cal.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,dayofMonth,month,year); //getactivity is used to get the context of the activity this fragment is associated to
//the above statement returns a new instance of the DatePickerdialog class
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {  //when date is set and OK is clicked
        MainActivity act = (MainActivity)getActivity();
        if (act != null) {
            act.processDatePicker(year, dayOfMonth, month);
        }
    }
}
