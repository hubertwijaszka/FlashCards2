package com.example.wijasyka.flashcards2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class PairsList extends AppCompatActivity {
    FlashDataBase db=new FlashDataBase(this);
    String tableName;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairs_list);
        TextView textView= (TextView) findViewById(R.id.textView);

        this.textView=textView;
        String r =getIntent().getStringExtra("table");
        this.tableName=r;
        Cursor w=db.readDate(r);
        while(w.moveToNext()){
            int nr=w.getInt(0);
            String e=w.getString(1);
            String p=w.getString(2);
            textView.setText(textView.getText().toString()+"\n"+ nr+" "+e+" "+p);
        }


    }
    public void onClickAddWord(View view){

        EditText eWord= (EditText) findViewById(R.id.eWord);
        EditText pWord= (EditText) findViewById(R.id.pWord);
        db.addWord(eWord.getText().toString(),pWord.getText().toString(),tableName);
        Cursor w=db.readDate(tableName);
        eWord.setText(" ");
        pWord.setText(" ");
        textView.setText("");
        while(w.moveToNext()){
            int nr=w.getInt(0);
            String e=w.getString(1);
            String p=w.getString(2);
            textView.setText(textView.getText().toString()+"\n"+ nr+" "+e+" "+p);
        }
    }


}
