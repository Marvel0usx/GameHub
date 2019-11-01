package com.example.userinterface.GameManager.HangMan;

import android.graphics.Color;
import android.widget.TextView;

public class AnswerKeyLetter {

    private char letter;

    private TextView textView;

    public AnswerKeyLetter(char letter) {
        this.letter = letter;
    }

    void addTextView(TextView textView) {
        this.textView = textView;
    }

    TextView getTextView(){
        return textView;
    }

    void turnBlack(){
        textView.setTextColor(Color.BLACK);
    }


}
