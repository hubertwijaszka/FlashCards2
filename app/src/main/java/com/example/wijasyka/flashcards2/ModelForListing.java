package com.example.wijasyka.flashcards2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by hp on 2018-02-25.
 */

public class ModelForListing  {

    private String name;
    private boolean isCheck;
    public ModelForListing(String name, boolean check){
        this.name=name;
        this.isCheck=check;
    }
    public String getName(){
        return name;
    }
    public boolean isCheck(){
        return isCheck;
    }
    public void setCheck(boolean bool){
        this.isCheck=bool;
    }
}
