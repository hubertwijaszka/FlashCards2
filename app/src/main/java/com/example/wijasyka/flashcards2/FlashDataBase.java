package com.example.wijasyka.flashcards2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.key;
import static android.R.id.primary;
import static android.content.ContentValues.TAG;
import static android.os.FileObserver.CREATE;

/**
 * Created by wijasyka on 2017-08-21.
 */

public final class FlashDataBase extends SQLiteOpenHelper {

        public FlashDataBase(Context context){
            super(context,DATABASE_NAME,null,1);
        }
        public static final String DATABASE_NAME="WordPairs.db";
        public static final String TABLE_NAME="Words";
        public static final String COL_1="id";
        public static final String COL_2="englishWords";
        public static final String COL_3="polishWords";

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                    + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_2 + " TEXT,"
                    + COL_3 + " TEXT"
                    + ");");

        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(db);
        }
        public void addWord(String eWord,String pWord,String tableName){
            SQLiteDatabase db=getWritableDatabase();
            ContentValues pair=new ContentValues();
            pair.put(COL_2,pWord);
            pair.put(COL_3,eWord);
            db.insertOrThrow(tableName, null, pair);
        }
        public Cursor readDate(String name){
            String[] column={COL_1,COL_2,COL_3};
            SQLiteDatabase db=getReadableDatabase();
            Cursor result=db.query(name,column,null,null,null,null,null);
            return result;
        }
        public  ArrayList nameTables(){
            SQLiteDatabase db=getWritableDatabase();
            Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
            ArrayList result=new ArrayList();
            if (c.moveToFirst()) {
                while ( !c.isAfterLast() ) {
                    if(!c.getString(0).equals("android_metadata")&&!c.getString(0).equals("sqlite_sequence"))
                     result.add(c.getString(0));
                    c.moveToNext();
                }
            }
            return result;
        }
        public void addNewTable(String nameTable){
            SQLiteDatabase db=getWritableDatabase();
            try{db.execSQL("CREATE TABLE " + nameTable + " ("
                    + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_2 + " TEXT,"
                    + COL_3 + " TEXT"
                    + ");");
        }
        catch(SQLException e){
            Log.e(TAG,e.getMessage());
    }}
        public void deleteTable(String tableName){
            SQLiteDatabase db=getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
        }
    }

