/*
* app demonstrates the use of Implicit intents, there are three implicit intents sent, one to open a link,another to open a location in Google maps and the third to share some text */
package com.example.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText website,location,share,dial_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        website = findViewById(R.id.website_editText);
        location = findViewById(R.id.loc_edittext);
        share = findViewById(R.id.share_edittext);
        dial_text = findViewById(R.id.dial_number);
        if(dial_text != null){
            dial_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if(actionId == EditorInfo.IME_ACTION_SEND){
                        handled = true;
                        dialnumber();
                    }
                    return handled;
                }
            });
        }

    }
    public void OpenWebsite(View view){
        String url = website.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if((intent.resolveActivity(getPackageManager())) != null){
            startActivity(intent);
        }
        else{
            Log.d("implicit_intent", "no app available to handle this request");
        }
    }
    public void openLocation(View view){
        String address = location.getText().toString();
        Uri add_uri = Uri.parse("geo:0,0?q=" + address);
        Intent intent = new Intent(Intent.ACTION_VIEW, add_uri);
        if((intent.resolveActivity(getPackageManager())) != null){
            startActivity(intent);
        }
        else{
            Log.d("implicit_intent", "no app available to handle this request");
        }
    }
    public void ShareText(View view){
        String text = share.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(text)
                .startChooser();
    }
    private void dialnumber(){
        String phone_num = null;
        if(dial_text != null){
            phone_num ="tel:" + dial_text.getText().toString();
            Uri uri = Uri.parse(phone_num);
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
            else{
                Log.d("idk","WHAT THE FUCK MAN");
            }
        }
    }
}
