package com.example.wijasyka.flashcards2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter implements View.OnClickListener {
    ArrayList<ModelForListing> modelItems = null;
    Context context;
    public CustomAdapter(Context context, ArrayList<ModelForListing> resource) {
        super(context,R.layout.layout,resource);
        this.context = context;
        this.modelItems = resource;
    }
    private static class ViewHolder {
       static TextView name;
       static CheckBox check;
    }


    @Override
    public void onClick(View v){
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ModelForListing dataModel=(ModelForListing) object;
        dataModel.setCheck(!dataModel.isCheck());
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ModelForListing model=(ModelForListing) getItem(position);
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.textView3);
            viewHolder.check = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ViewHolder.name.setText(model.getName());
        ViewHolder.check.setChecked(model.isCheck());
        ViewHolder.check.setOnClickListener(this);
        ViewHolder.check.setTag(position);
        return convertView;
    }
}
