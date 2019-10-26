package com.example.userinterface.HangMan;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;
import java.util.Random;
import com.example.userinterface.R;

public class HangManGameActivity extends Activity{
    private ImageView[] balloons; // balloon images
    private int numLives = 6; // number of lives
    private int keywordLen; // number of characters in current word
    private int remainingBallon; // number of balloons left
    private int numCorr; // number of letters correctly guessed

    private String[] words;
    private Random rand;
    private String currentWord;
    private LinearLayout wordLayout;
    private TextView[] characterViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_activity_game);
        Resources res = getResources();
        words = res.getStringArray(R.array.words);
        rand = new Random();
        currentWord = "";
        wordLayout = (LinearLayout)findViewById(R.id.word);
        playHangMan();
    }

    private void playHangMan(){
        // plays a new HangMan game
        String newWord = words[rand.nextInt(words.length)];
        currentWord = newWord;
        characterViews = new TextView[currentWord.length()];
        wordLayout.removeAllViews();

        // create a text view for each character of the letter

        for (int c = 0; c < currentWord.length(); c++) {
            characterViews[c] = new TextView(this);
            characterViews[c].setText(""+currentWord.charAt(c));

            characterViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            characterViews[c].setGravity(Gravity.CENTER);
            characterViews[c].setTextColor(Color.WHITE);
            characterViews[c].setBackgroundResource(R.drawable.hm_letter_background);
            //add to layout
            wordLayout.addView(characterViews[c]);
        }
    }




}
