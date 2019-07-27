package com.example.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>{
    /*wordviewholder is a class to hold views */
    private final LinkedList<String> wordlist;
    private LayoutInflater inflater;
    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {   //this method creates a view and returns it, ot also inflates the layout
        View itemv = inflater.inflate(R.layout.wordlist_item, viewGroup,false );
        Log.i("Hey", "I am the oncreateviewholder");
        return new WordViewHolder(itemv, this);

    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder wordViewHolder, int i) {
        String message = wordlist.get(i);             //this method binds the data to the view and sets the textview in wordviewholder to message
        wordViewHolder.wordItem.setText(message);
        Log.i("hey", "I am the on bind holder function");
    }
    public WordListAdapter(Context context, LinkedList<String> string){
        inflater = LayoutInflater.from(context);     //constructor for wordlistadapter,getting inflater and setting the value of the passed linkedlist
        this.wordlist = string;
        Log.i("hey", "I am the constructor of wordlistadapter" );
    }

    @Override
    public int getItemCount() {
        //this method returns the size of the data
        Log.i("Hey", "I am the getcount function");
        return wordlist.size();
    }
    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {   //viewholder class is generally defined as an inner class
        public final TextView wordItem;
        public final WordListAdapter adapter;
        public WordViewHolder(View item, WordListAdapter adapter){
            super(item);
            wordItem = item.findViewById(R.id.word);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
            Log.i("hey", "I am the constructor for wordviewholder");
        }

        @Override
        public void onClick(View v) {
        //getting position of the item clicked
            int pos =  getLayoutPosition();
            String text = wordlist.get(pos);
            wordlist.set(pos, "clicked!! " + text);
            adapter.notifyDataSetChanged();
            Log.i("hey", "im the onclick listener");
        }
    }
}
