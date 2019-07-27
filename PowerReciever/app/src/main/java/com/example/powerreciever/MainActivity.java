/*This is an app that implements Broadcast Receivers in Android. It displays a Toast Message when power is disconnected or connected, it also displays Toast messages when the headset
* is plugged or unplugged*/



package com.example.powerreciever;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private CustomReceiver customReciever = new CustomReceiver();
    static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        this.registerReceiver(customReciever, filter); //registering the receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(customReciever, new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }
    @Override
    protected void onDestroy(){
        //broadcast receivers need to be unregistered when an app or activity is destroyed
        this.unregisterReceiver(customReciever);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(customReciever);
        super.onDestroy();
    }
    public void sendCustomBroadcast(View v){
        //will use local broadcasts as app is sending itself broadcasts
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
         int val = rand.nextInt(20) + 1;
        customBroadcastIntent.putExtra("the value", "the square of " + val + "is " + val*val);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);

    }
}
