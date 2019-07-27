package com.example.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public String order;
    public static final String EXTRA_MESSAGE = "com.example.droidcafe.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, order);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_order:
                DisplayToast(getString(R.string.action_order_message));
                Intent intent = new Intent(this, OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, order);
                startActivity(intent);
                return true;
            case R.id.action_status:
                DisplayToast(getString(R.string.action_status_message));
                return true;
            case R.id.action_favourites:
                DisplayToast(getString(R.string.action_favorites_message));
                return true;
            case R.id.action_contact:
                DisplayToast(getString(R.string.action_contact_message));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void DisplayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showDonut(View view) {
        DisplayToast(getString(R.string.donut_order_message));
        order = getString(R.string.donut_order_message);
    }

    public void IceCreamorder(View view) {
        DisplayToast(getString(R.string.ice_cream_order_message));
        order = getString(R.string.ice_cream_order_message);
    }

    public void froyoshoworder(View view) {
        DisplayToast(getString(R.string.froyo_order_message));
        order = getString(R.string.froyo_order_message);
    }
}
