package com.dit.brandon.assignment;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class ListBooks extends AppCompatActivity {
    private DatabaseHelper myDb;
    private SimpleCursorAdapter dataAdapter;
    ListView list;
    ArrayList<String> array = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new DatabaseHelper(this);
        list=(ListView)findViewById(R.id.listView);


        array = myDb.getAllData();
        setList();

        // ViewBooks();
    }


    public void setList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,array);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String valueInItem = (String) list.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(),bookinfo.class);
                i.putExtra("bookData", valueInItem);
                startActivity(i);

            }
        });


    }
}