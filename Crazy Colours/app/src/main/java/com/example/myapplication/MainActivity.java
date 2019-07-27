/*app displays the number of times a button has been clicked in random colours(from a set of 20 such colours)*/

package com.example.myapplication;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView hello_text;
    private String[] colors = { "red","pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            hello_text.setTextColor(savedInstanceState.getInt("color"));    //getting the last saved color
        }
        setContentView(R.layout.activity_main);
        hello_text = findViewById(R.id.hello_text_view);
    }
    public void changecolor(View view) {
        Random random = new Random();
        String colorName = colors[random.nextInt(20)];      //creating a random number between 0 and 19
        int colorResourceName = getResources().getIdentifier(colorName, "color",getApplicationContext().getPackageName());  //getting resourcename for that color with key color
        int colorRes = ContextCompat.getColor(this,colorResourceName);   //contextcompat provides methods for compatibility, the getcolor method here takes two arguments and returns the integer id of the color
        hello_text.setTextColor(colorRes);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("color", hello_text.getCurrentTextColor());    // saving the color of text using key:color
    }
}
