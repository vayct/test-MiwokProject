package com.example.android.miwok;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Jet on 5/25/2017.
 */

public class Word {

    String mDefaultTranslation;
    String mMiwokTranslation;

    public Word(String defaultTranslation, String miwokTranslation)
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }


}
