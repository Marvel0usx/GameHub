package com.example.userinterface.HangMan;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class HangManGameActivity extends Activity {
    private ImageView[] balloons; // balloon images
    private int numLives = 6; // number of lives
    private int keywordLen; // number of characters in current word
    private int remainingBallon; // number of balloons left
    private int numCorr; // number of letters correctly guessed

}
