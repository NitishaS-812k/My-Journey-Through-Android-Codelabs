package com.example.powerreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static com.example.powerreciever.MainActivity.ACTION_CUSTOM_BROADCAST;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String get = intent.getAction();
        if(get != null){
            String message = "Unknown Intent action";
            switch(get){
                case Intent.ACTION_POWER_CONNECTED:
                    message = "Power Connected";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    message = "Power Disconnected";
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    String text = intent.getStringExtra("the value");
                    message = "custom Broadcast received\n" + text;
                    break;
                case Intent.ACTION_BATTERY_LOW:
                    message = "Battery Low";
                    break;
                case Intent.ACTION_HEADSET_PLUG:
                    message = "Headset plugged!!";
                    break;
                default:
                    message = "null";
                    break;
            }
            Toast.makeText(context, message,Toast.LENGTH_SHORT).show();
        }

    }
}
