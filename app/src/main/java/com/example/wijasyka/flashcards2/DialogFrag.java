package com.example.wijasyka.flashcards2;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;




public class DialogFrag extends android.support.v4.app.DialogFragment implements View.OnClickListener {

    Comunicator comunicator;
    EditText tableName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_dialog, container, false);

       Button addButton=(Button) view.findViewById(R.id.addButton);
        tableName=(EditText) view.findViewById(R.id.tablename35);
       addButton.setOnClickListener(this);
        return view;

    }
    @Override
    public void onAttach(Context context){
        Activity a;
        a=(Activity)context;
        super.onAttach((context));
        comunicator= (Comunicator) a;
    }
  public  void onClick(View view){

      String tableNameFromEditText=new String(tableName.getText().toString());
      comunicator.messageFromFragment(tableNameFromEditText);
      getDialog().dismiss();

    }
    interface Comunicator{
        public void messageFromFragment(String message);
    }


}
