package com.example.wijasyka.flashcards2;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Path;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import static android.R.id.list;
import static java.lang.Boolean.FALSE;

public class listActivity extends AppCompatActivity implements DialogFrag.Comunicator {
FlashDataBase db=new FlashDataBase(this);
ListView listView;
ArrayList<ModelForListing> items;
CustomAdapter adapter;
    FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final ArrayList<String> namesOfFiles= db.nameTables();
        items=new ArrayList<ModelForListing>();
        for(String item : namesOfFiles ){
            items.add(new ModelForListing(item,false));
        }
        ListView listView=(ListView) findViewById(R.id.listView);
        this.listView=listView;
        CustomAdapter adapter = new CustomAdapter(this,items);
        this.adapter=adapter;
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String table=items.get(position).getName();
                Intent intent=new Intent(view.getContext(), PairsList.class);
                intent.putExtra("table", table);
                startActivity(intent);
    }
        }
        );
    }


    public void onClickAddSetButton(View view){
        FragmentManager manager=getSupportFragmentManager();
        DialogFrag dialog=new DialogFrag();
        dialog.show(manager,"adadad");


    }

    public void onClickDeleteButton(View view){
        ArrayList<ModelForListing> itemsToDelete=new ArrayList<>();
        for(ModelForListing i:items){
            if(i.isCheck()) {
                itemsToDelete.add(i);
                db.deleteTable(i.getName());
            }
        }
        for(ModelForListing o:itemsToDelete){
            items.remove(o);
        }
        adapter.notifyDataSetChanged();
    }
    public void onClickAddFromFile(View view){

        SQLiteDatabase db = this.db.getWritableDatabase();
        db.beginTransaction();
        try {
            ///////////////////////////////////read storage permission
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                }
            }
            /////////////////////////////////////////////////////////////////////
            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File(sdcard,"nowy.txt");
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length != 2) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
            }
                String[] str = line.split(",", 2);
                String nationalWord = str[1].toString();
                String foreginWord = str[0].toString();
                this.db.addWord(foreginWord,nationalWord,"costam2");
            }
        } catch (IOException e) {
            this.showDialogs(R.string.databaseError);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void messageFromFragment(String message){
       try{
           db.addNewTable(message);
            items.add(new ModelForListing(message,FALSE));
            adapter.notifyDataSetChanged();}
       catch(Exception e){
            this.showDialogs(R.string.databaseError);
        }
      }



    private  void showDialogs(int string){
        DialogFragment newFragment = ErrorFrag.newInstance(
                string);
        newFragment.show(fm, "dialog");

    }

}




