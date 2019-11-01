package com.example.userinterface.GameManager.HangMan;

import android.graphics.Color;
import android.widget.TextView;

/**
 * A letter in the answer key.
 */
public class AnswerKeyLetter {

    protected char letter; // the character of the AnswerKeyLetter
    private TextView textView; // the text view of

    /**
     * Initialize a new answer key letter.
     * @param letter
     */
    public AnswerKeyLetter(char letter) {
        this.letter = letter;
    }

    /**
     * Add a text view to the answer key letter.
     * @param textView
     */
    void addTextView(TextView textView) {
        this.textView = textView;
    }

    /**
     * Get the text view of the answer key letter.
     * @return textView
     */
    TextView getTextView(){
        return textView;
    }

    /**
     * Turn the color of the answer key letter to black.
     */
    void turnBlack(){
        textView.setTextColor(Color.BLACK);
    }


}
