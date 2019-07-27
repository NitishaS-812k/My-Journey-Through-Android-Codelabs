package com.example.hellotoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    private int mcount = 0;
    private TextView showcount;
    private Button butt_zero;
    private  static final String text = "helloToast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showcount = findViewById(R.id.ShowCount);
        butt_zero = findViewById(R.id.buttonreset);
    }
    public void show_Toast(View view){
        Toast toast = makeText(this, R.string.thing, Toast.LENGTH_SHORT);
        toast.show();

    }
    public void counter(View view){
        mcount++;
        butt_zero.setVisibility(butt_zero.VISIBLE);
        if(showcount!= null)
        {
            showcount.setText(Integer.toString(mcount));

        }


    }
    public void reset(View view){
        mcount = 0;
        showcount.setText(R.string.zero);
        view.setVisibility(view.INVISIBLE);
    }

}
