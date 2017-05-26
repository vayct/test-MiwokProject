package com.example.android.miwok;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jet on 5/26/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {


    public WordAdapter(Activity context, ArrayList<Word> words){

        super(context, 0 , words );
    }


    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        //get the object located at this position in the list
        Word currentWord = getItem(position);

        //find the textViw in the list_item.xml layout with the default_text_view
        TextView defaultWordTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        //Get the version name from the current object and set this text on the TextView
        defaultWordTextView.setText(currentWord.getDefaultTranslation());

        //find the textView in the list_item.xml layout with the miwok_text_view
        TextView miworkWordTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miworkWordTextView.setText(currentWord.getMiwokTranslation());

        return listItemView;



    }
}
