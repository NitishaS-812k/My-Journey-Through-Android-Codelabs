package com.example.recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> wordList = new LinkedList<>();
    private RecyclerView recyclerView;
    private WordListAdapter wordListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView =findViewById(R.id.recycler_view);
        wordListAdapter = new WordListAdapter(this, wordList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int size = wordList.size();
               wordList.addLast("word" + size);
               recyclerView.getAdapter().notifyItemInserted(size);
               recyclerView.smoothScrollToPosition(size);
            }
        });
        initialize();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void initialize(){
        wordList.clear();
        for(int i = 0; i< 20; i++){
            wordList.addLast("word" + i);
        }
        recyclerView.setAdapter(wordListAdapter);
        recyclerView.smoothScrollToPosition(0); //scrolling smoothly to position 0
        wordListAdapter.notifyDataSetChanged();
    }

    public void initialize(MenuItem item) {
        if(item.getItemId() == R.id.action_reset)
        {
            initialize();
        }
    }
}
