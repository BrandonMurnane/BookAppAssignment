package com.dit.brandon.assignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class AddBook extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText editName, editAuthor, editSeries;
    RadioGroup editState;
    RadioButton editStateButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.BookName_EditText);
        editAuthor = (EditText) findViewById(R.id.AuthName_EditText);
        editSeries = (EditText) findViewById(R.id.SName_EditText);

        editState = (RadioGroup) findViewById(R.id.BookInforadioGroup);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void SubmitBook(View view) {
        int selectedID = editState.getCheckedRadioButtonId();
        editStateButton = (RadioButton) findViewById(selectedID);


        Boolean isInserted = myDb.insertData(editName.getText().toString(),
                editAuthor.getText().toString(),
                editSeries.getText().toString(),
                editStateButton.getText().toString());
        if (isInserted = true) {
            Toast.makeText(AddBook.this, "Book Inserted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddBook.this, MainActivity.class);
            startActivity(intent);
        }


    }

    public void BackButton(View view) {
        Intent intent = new Intent(AddBook.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddBook Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.dit.brandon.assignment/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddBook Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.dit.brandon.assignment/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
