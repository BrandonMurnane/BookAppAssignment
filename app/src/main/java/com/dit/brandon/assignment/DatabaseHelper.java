package com.dit.brandon.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Brandon on 26/11/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Database_Name="Assignmentapp.db";
    public static final String Table_Name= "Book_Table";

    public static final String Col_1= "ID";
    public static final String Col_2= "Name";
    public static final String Col_3= "Author";
    public static final String Col_4= "Series";
    public static final String Col_5= "State";

    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Name + "(" + Col_1 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE ," + Col_2 + " Text," + Col_3 + " Text," + Col_4 + " Text," + Col_5 + " Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists"+Table_Name);
        onCreate(db);
    }

    public boolean insertData(String Name,String Author,String Series, String State ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,Name);
        contentValues.put(Col_3,Author);
        contentValues.put(Col_4,Series);
        contentValues.put(Col_5, State);

        long result =db.insert(Table_Name,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public ArrayList<String> getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name, null);
        ArrayList<String> Books = new ArrayList<String>();
        if(res.moveToFirst()){
            do{
                Books.add("Title: "+res.getString(res.getColumnIndex(Col_2))+"\nAuthor:" +res.getString(res.getColumnIndex(Col_3))+"\nSeries:"+res.getString(res.getColumnIndex(Col_4))+"\nState:"+res.getString(res.getColumnIndex(Col_5))  );
            }while(res.moveToNext());

        }
        return Books;

    }
}
