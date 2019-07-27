/*This app demonstrates a Date Picker and a Time Picker using fragments and it displays a Toast message when the user select the date or time*/

package com.example.datepicker;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showdatePicker(View v) {
        DialogFragment newFragment = new DatePicker();
        newFragment.show(getSupportFragmentManager(), "datepicker"); //getsuppoortfragmentmanager is a method used to access fragments
    }

    public void processDatePicker(int year, int day, int month) {
        String Month = Integer.toString(month + 1);
        String Day = Integer.toString(day);    //processing input date
        String Year = Integer.toString(year);
        String date = Day + "/" + Month + "/" + Year;
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
    }

    public void showTimepicker(View view) {
        DialogFragment dialogFragment = new TimePicker();
        dialogFragment.show(getSupportFragmentManager(), "timepicker");
    }

    public void processTimePicker(int hour, int minute) {
        String Hour = Integer.toString(hour);
        String Minute = Integer.toString(minute);
        String Time = Hour + ":" + Minute;
        Toast.makeText(this, Time, Toast.LENGTH_SHORT).show();
    }
}
