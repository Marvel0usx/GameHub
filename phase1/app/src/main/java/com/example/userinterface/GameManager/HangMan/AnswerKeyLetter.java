package com.example.userinterface.GameManager.HangMan;

import android.graphics.Color;
import android.widget.TextView;

public class AnswerKeyLetter {

    protected char letter;

    private TextView textView;

    private boolean shown;

    public AnswerKeyLetter(char letter) {
        this.letter = letter;
        shown = false;
    }

    void addTextView(TextView textView) {
        this.textView = textView;
    }

    TextView getTextView(){
        return textView;
    }

    //void showLetter(){
     //   shown = true;
      //  if (shown) {
       //     this.turnBlack();
      //  }
   // }

    void turnBlack(){
        textView.setTextColor(Color.BLACK);
    }


}
