package com.example.scrollingtext;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView article = findViewById(R.id.article);
        registerForContextMenu(article);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_share:
                Toast.makeText(this,"Share Option Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_edit:
                Toast.makeText(this, "Edit Option Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_delete:
                Toast.makeText(this,"Delete Option Selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }
}
