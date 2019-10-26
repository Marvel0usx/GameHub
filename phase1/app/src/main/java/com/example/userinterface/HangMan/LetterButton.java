package com.example.userinterface.HangMan;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import com.example.userinterface.R;
import android.view.View;
import android.view.ViewGroup;


public class LetterButton extends BaseAdapter {

    private char[] alphabet;
    private LayoutInflater letterInflator;

    public LetterButton(Context c) {
        //setup buttons
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        letterInflator = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return alphabet.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //create a button for the letter at this position in the alphabet
        Button letterBtn;
        if (convertView == null) {
            //inflate the button layout
            letterBtn = (Button)letterInflator.inflate(R.layout.hm_letters, parent, false);
        } else {
            letterBtn = (Button) convertView;
        }
        //set the text to this letter
        letterBtn.setText(alphabet[position]);
        return letterBtn;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }




}
