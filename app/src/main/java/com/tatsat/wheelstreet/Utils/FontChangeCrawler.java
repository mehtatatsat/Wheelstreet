package com.tatsat.wheelstreet.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Admin1 on 2/16/2016.
 */
public class FontChangeCrawler {

    public Typeface DEFAULT;
    public Typeface DEFAULT_BOLD;

    public FontChangeCrawler(Typeface typeface) {
        this.DEFAULT = typeface;
        this.DEFAULT_BOLD = typeface;
    }

    public FontChangeCrawler(Context context, AssetManager assets, String assetsFontFileName, String boldAssetFontName) {
        DEFAULT = FontCache.get(assetsFontFileName, context);
        DEFAULT_BOLD = FontCache.get(boldAssetFontName, context);


//        typeface = Typeface.createFromAsset(assets, assetsFontFileName);
    }

    public void replaceFonts(ViewGroup viewTree) {
        View child;
        for (int i = 0; i < viewTree.getChildCount(); ++i) {
            child = viewTree.getChildAt(i);
            if (child instanceof ViewGroup) {
                // recursive call
                replaceFonts((ViewGroup) child);
            } else {
                setTypeface(child);
            }
        }
    }


    private void setTypeface(View view) {
        if (view instanceof TextView) {
            if (view instanceof TextView) {

                // base case
//                if(typeface!=null){
//                ((TextView) child).setTypeface(typeface);}

                TextView textView = (TextView) view;
                Typeface typeface = textView.getTypeface();
                if (typeface != null && typeface.isBold()) {
                    textView.setTypeface(DEFAULT_BOLD);
                } else {
                    textView.setTypeface(DEFAULT);
                }
            } else if (view instanceof Button) {
                Button buttonView = (Button) view;
                Typeface typeface = buttonView.getTypeface();
                if (typeface != null && typeface.isBold()) {
                    buttonView.setTypeface(DEFAULT_BOLD);
                } else {
                    buttonView.setTypeface(DEFAULT);
                }
            } else if (view instanceof EditText) {
                EditText editTextView = (EditText) view;
                Typeface typeface = editTextView.getTypeface();
                if (typeface != null && typeface.isBold()) {
                    editTextView.setTypeface(DEFAULT_BOLD);
                } else {
                    editTextView.setTypeface(DEFAULT);
                }
            } else if (view instanceof CheckBox) {
                CheckBox checkBoxView = (CheckBox) view;
                Typeface typeface = checkBoxView.getTypeface();
                if (typeface != null && typeface.isBold()) {
                    checkBoxView.setTypeface(DEFAULT_BOLD);
                } else {
                    checkBoxView.setTypeface(DEFAULT);
                }
            }


        }
    }
}