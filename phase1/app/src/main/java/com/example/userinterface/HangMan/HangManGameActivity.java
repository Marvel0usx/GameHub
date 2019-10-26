package com.example.userinterface.HangMan;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import android.widget.GridView;

public class HangManGameActivity extends Activity{
    private ImageView[] balloons; // balloon images
    private int numLives = 6; // number of lives
    private int keywordLen; // number of characters in current word
    private int remainingBallon; // number of balloons left
    private int numCorr; // number of letters correctly guessed

    private GridView alphabet;
    private LetterButton letterButton;

    private String[] words;
    private Random rand;
    private String currentWord;
    private LinearLayout wordLayout;
    private TextView[] characterViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_activity_game);
        playHangMan();
    }

    public void buttonOnClick(View v){
        Button button = (Button) v;
        ((Button) v).setText("C");
    }



    private void playHangMan(){
        // plays a new HangMan game
        String newWord = words[rand.nextInt(words.length)];
        currentWord = newWord;
        characterViews = new TextView[currentWord.length()];
        wordLayout.removeAllViews();

        // create a text view for each character of the letter





}
