package com.example.android.miwok;


import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jet on 5/26/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mBackgroundColor;

    public WordAdapter(Activity context, ArrayList<Word> words, int backgroundColor){

        super(context, 0 , words );
        mBackgroundColor = backgroundColor;
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




        //set up the image view for each word in Colors, Family, and Numbers
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);

        if(currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else
            imageView.setVisibility(View.GONE);

        //set the theme color of the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mBackgroundColor);
        textContainer.setBackgroundColor(color);





        return listItemView;



    }
}
