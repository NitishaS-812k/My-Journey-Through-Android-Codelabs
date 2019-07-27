/*This is an app that sends Notifications to the user, it has three buttons in the main Activity, which are Update, Cancel and Notify. Clicking Notify sends a notification,
* pressing Update updates the notification with an image in the BigPictureStyle , pressing cancel clears the notification. the States of the buttons are toggled accordingly
*/









package com.example.notify;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_notify, button_cancel, button_update;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    //channel id for notification channel, each notification channel uses a unique String as an ID
    private NotificationManager notificationManager;  //notificationmanager is a class used to send the user some notification
    private static final int NOTIFICATION_ID = 0; //to build notification we need to register it with a id
    private static final String ACTION_UPDATE_NOTIFICATION = "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";//constant string for updating notification broadcast
    private static final String ACTION_DISMISS ="com.example.android.notifyme.ACTION_DISMISS";
    private NotificationReceiver notificationReceiver = new NotificationReceiver();

    private NotificationCompat.Builder getNotificationBuilder() {
        //new intent
        Intent notificationIntent = new Intent(this, MainActivity.class); //intent to launch the main activity
        //pending intent does an action in future
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //definition of get notification builder
        NotificationCompat.Builder notifybuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID) //creating new notification builder
                .setContentTitle("This is the Extraordinary notification")           //setting title of notification
                .setContentText("To the Extraordinary user!")  // setting description of notification
                .setSmallIcon(R.drawable.ic_android)//setting notification icon
                .setContentIntent(notificationPendingIntent)//setting content intent to the predefined pending intent
                .setAutoCancel(true) //auto cancel closes the notification when user taps on it
                .setPriority(NotificationCompat.PRIORITY_HIGH)//setting priority for backward compatibility, priority for android>8 can be set in create notification!
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        return notifybuilder; //returning the notification builder object
    }

    void setButtonState(Boolean notify_enabled, Boolean cancel_enabled, Boolean update_enabled) {
        button_notify.setEnabled(notify_enabled);
        button_cancel.setEnabled(cancel_enabled);
        button_update.setEnabled(update_enabled);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_notify = findViewById(R.id.notify);
        button_cancel = findViewById(R.id.cancel);
        button_update = findViewById(R.id.update);
        button_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_notification();
            }
        });
        createNotificationChannel();//calling the function that creates the channel for the notification
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_notification();
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel_notification();
            }
        });
        setButtonState(true, false, false);
        registerReceiver(notificationReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION)); //registering receiver for the broadcast
    }

    public void send_notification() {
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,NOTIFICATION_ID,updateIntent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();//calling getnotificationbuilder in onCreate
        notifyBuilder.addAction(R.drawable.ic_action_update, "Update Notification",pendingIntent);
        notificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());  //building the notification with Notification id
        setButtonState(false, true, true);
    }

    public void createNotificationChannel() {  //create notification channel
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "extraordinary", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GRAY);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from the extraordinary");
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void update_notification() {
        Bitmap androidImage = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1); //converting picture to bitmap
        NotificationCompat.Builder notifybuilder = getNotificationBuilder();
        notifybuilder.setStyle(new NotificationCompat.BigPictureStyle() //bigpicture style is a subclass of notification compat to provide additonal styles
                .bigPicture(androidImage)
                .setBigContentTitle("Notification Updated!"));
        notificationManager.notify(NOTIFICATION_ID, notifybuilder.build());
        setButtonState(false, true, false);
    }

    public void cancel_notification() {
        notificationManager.cancel(NOTIFICATION_ID); //cancelling the notification
        setButtonState(true, false, false);
    }

    public class NotificationReceiver extends BroadcastReceiver {
        public NotificationReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String get = intent.getAction();
            if(get != null){
                switch(get){
                    case ACTION_UPDATE_NOTIFICATION:
                        update_notification();
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(notificationReceiver);
        super.onDestroy();
    }
}
