/*
* This is an app that sends a notification, pr rather an alarm if the user presses the alarm button every 15 minutes.*/
package com.example.alarmmanager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES; //time in millisecinds
        final long triggerTime = SystemClock.elapsedRealtime()+ repeatInterval;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToggleButton alarm = findViewById(R.id.alarm_toggle);
        final AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);
        final Intent notifyIntent = new Intent(this, AlarmReceiver.class); //intent to go to the broadcast, wrapped up nicely in a pending intent
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);
        alarm.setChecked(alarmUp);
        final PendingIntent notifypendingIntent = PendingIntent.getBroadcast(this,NOTIFICATION_ID,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT); //pending intent to send broadcast
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        alarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String ToastMessage;
                if(isChecked) {
                    ToastMessage = getString(R.string.alarm_on);
                    if (alarmManager != null) {
                        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval, notifypendingIntent); //repeat alarm every 15 minutes by sending a broadcast
                }
                    else{
                        notificationManager.cancelAll();
                        ToastMessage = getString(R.string.alarm_off);
                        if (alarmManager != null) {
                            alarmManager.cancel(notifypendingIntent); //cancel alarm when alarm off is pressed
                        }
                    }
                Toast.makeText(getApplicationContext(),ToastMessage,Toast.LENGTH_SHORT).show();
            }
        }});
                createNotificationChannel();
    }
    public void createNotificationChannel(){
        //creating Notification channel for android 8 or higher
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,"ALARM NOTIFICATION",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setDescription("To Bother you every 15 Minutes");
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
