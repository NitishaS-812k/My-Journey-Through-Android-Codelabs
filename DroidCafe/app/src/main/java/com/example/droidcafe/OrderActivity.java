package com.example.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RadioButton button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        String message = "Order :" + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textview = findViewById(R.id.show_order);
        textview.setText(message);
        button1 = findViewById(R.id.next_day);
        button2 = findViewById(R.id.sameday);
        button3 = findViewById(R.id.pick_up);
        if ((!button1.isChecked()) && (!button2.isChecked()) && (!button3.isChecked())) {
            button1.setChecked(true); //setting bydefault button 2 checked
        }
        Spinner spinner = findViewById(R.id.spinner);
        if(spinner != null){
            spinner.setOnItemSelectedListener(this);   //listener for deciding item is selected or not
        }
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.labels_array,android.R.layout.simple_spinner_item);  //creating adapter from resource array and attaching simple item layout to each
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //attaching the dropdown layout to the spinner
        if(spinner != null){
            spinner.setAdapter(arrayAdapter); //attaching the adapter to the spinner
        }
    }

    public void onRadioButtonClick(View view) {
        switch (view.getId()) {
            case R.id.sameday:
                displayToast(getString(R.string.same_day));
                break;
            case R.id.next_day:
                displayToast(getString(R.string.next_day));
                break;
            case R.id.pick_up:
                displayToast(getString(R.string.pick_up));
                break;
            default:
                break;
        }
    }

    private void displayToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerLabel = parent.getItemAtPosition(position).toString();   //when some item is selected this method is called
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}