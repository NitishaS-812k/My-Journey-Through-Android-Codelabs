/*This is an app that demonstrates implici intent, there are two fields call and text, pressing call leads the user to the default "Phone"application while clicking "Text"
* leads the user to the default SMS application "Messages and the respective information entered by the user in this app is already in the given fields of the "phone" and "messsages app*/


package com.example.call_and_text;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public EditText num,mess;
    public String number, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = findViewById(R.id.enter_phone);
        mess =findViewById(R.id.enter_text);
    }
    public void call(View view){
        number = num.getText().toString();
        String num = "tel:" + number;
        Log.d("NUMBER",num);
        Intent call_intent = new Intent(Intent.ACTION_DIAL);
        call_intent.setData(Uri.parse(num));
        if(call_intent.resolveActivity(getPackageManager()) != null){
            startActivity(call_intent);
        }
    }
    public void sms(View view){
        message = mess.getText().toString();
        number = num.getText().toString();
        String sendto= "smsto:" + number;
        Log.d("NUMBER", sendto);
        Intent sms_intent = new Intent(Intent.ACTION_SENDTO);
        sms_intent.setData(Uri.parse(sendto));
        sms_intent.putExtra("sms_body", message);
        Log.d("TEXT",message);
        if(sms_intent.resolveActivity(getPackageManager()) != null){
            startActivity(sms_intent);
        }
    }
}
