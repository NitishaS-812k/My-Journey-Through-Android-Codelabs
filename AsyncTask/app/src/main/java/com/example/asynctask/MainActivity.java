/*This is an app that uses Asynctask to work(sleep) in the background and later after sleeping for a random amount of time it updates the user that it slept for some milliseconds*/


package com.example.asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private static final String TEXT_STATE = "currentText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.TextView1);
        if (savedInstanceState != null) {
            text.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void starttask(View view) {
        text.setText(R.string.napping);
        new SimpleAsyncTask(text).execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putString(TEXT_STATE, text.getText().toString());
    }
}
