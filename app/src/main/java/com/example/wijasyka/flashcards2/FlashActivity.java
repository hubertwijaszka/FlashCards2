package com.example.wijasyka.flashcards2;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.support.design.R.id.text;

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    public void AppendFile(View view){
      /*  EditText p= (EditText) findViewById(R.id.editText);
        EditText e= (EditText) findViewById(R.id.editText2);
        String p2=p.getText().toString();
        String e2=e.getText().toString();
        FlashDataBase db=new FlashDataBase(this);
       try {
           db.addWords(e2, p2);
       }
       catch (Exception a){
           TextView costam=(TextView) findViewById(R.id.textView2);
           costam.setText(a.getMessage());
       }*/
    }
}
