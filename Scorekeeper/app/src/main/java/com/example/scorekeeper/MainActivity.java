package com.example.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int score1,score2;
    private TextView tscore1, tscore2;
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tscore1 = findViewById(R.id.score_1);
        tscore2 = findViewById(R.id.score_2);
        if(savedInstanceState != null){
           score1 = savedInstanceState.getInt(STATE_SCORE_1);
           score2 = savedInstanceState.getInt(STATE_SCORE_2);
            tscore1.setText(String.valueOf(score1));
            tscore2.setText(String.valueOf(score2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        }
        else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.night_mode) {
            int NightMode = AppCompatDelegate.getDefaultNightMode();
            if (NightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
        recreate();
        return true;
    }

    public void IncreaseScore(View view){
        switch(view.getId()){
            case R.id.increasingTeam1:
                score1++;
                tscore1.setText(String.valueOf(score1));
                break;
            case R.id.increasingTeam2:
                score2++;
                tscore2.setText(String.valueOf(score2));
                break;
        }
    }
    public void decreaseScore(View view){
        int viewId = view.getId();
        switch(viewId){
            case R.id.decreasingTeam1:
                score1--;
                tscore1.setText(String.valueOf(score1));
                break;
            case R.id.decreasingTeam2:
                score2--;
                tscore2.setText(String.valueOf(score2));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SCORE_1,score1);
        outState.putInt(STATE_SCORE_2,score2);
        super.onSaveInstanceState(outState);
    }
}
