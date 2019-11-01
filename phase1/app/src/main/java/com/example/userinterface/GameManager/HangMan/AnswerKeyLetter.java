package com.example.userinterface.GameManager.HangMan;

import android.widget.TextView;

public class AnswerKeyLetter {

    private char letter;
    private boolean shown;

    public AnswerKeyLetter(char letter) {
        this.letter = letter;
    }

    void turnBlack(char ltr){
        shown = true;
    }


}
