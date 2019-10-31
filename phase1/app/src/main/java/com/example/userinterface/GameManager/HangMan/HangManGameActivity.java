package com.example.userinterface.GameManager.HangMan;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;

import java.util.Random;

import com.example.userinterface.GameManager.GameManager;
import com.example.userinterface.GameManager.Games;
import com.example.userinterface.GameManager.MenuActivity;
import com.example.userinterface.GameManager.TowerDefense.TowerDefenseActivity;
import com.example.userinterface.GameManager.User;
import com.example.userinterface.R;

import android.widget.GridView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class HangManGameActivity extends Activity {
    private ImageView[] balloons; // balloon images
    private int numLives = 6; // number of lives
    private int currLen; // number of characters in current word
    private int remainingBallons; // number of balloons left, decreases each time a wrong letter is guessed
    private int numCorr; // number of letters correctly guessed

    private String[] words;
    private Random rand;
    private String currentWord;
    private LinearLayout wordLayout;
    private TextView[] characterViews;
    private Games gameManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_activity_gameee);
        balloons = new ImageView[numLives];
        // initializes images containing different numbers of ballons, representing different number
        // of lives.
        rand = new Random();
        currentWord = "";
        wordLayout = findViewById(R.id.word);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            gameManager = (Games) bundle.getSerializable("Game");
        }

        balloons[0] = findViewById(R.id.ballon1);
        balloons[1] = findViewById(R.id.ballon2);
        balloons[2] = findViewById(R.id.ballon3);
        balloons[3] = findViewById(R.id.ballon4);
        balloons[4] = findViewById(R.id.ballon5);
        balloons[5] = findViewById(R.id.ballon6);
        playHangMan();
    }

    @Override
    public void onBackPressed() {
        gameManager.reLocate(HangManGameActivity.this, 1, 0);
    }


    public void makeGuess(View v) {
        // the user has clicked on the letter he/she wants to guess
        String letterGuessed = ((TextView) v).getText().toString();
        char charGuessed = letterGuessed.charAt(0);
        v.setEnabled(false);
        v.setBackgroundResource(R.drawable.hm_letter_clicked);

        boolean correct = false;

        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == charGuessed) {
                correct = true;
                numCorr++;
                characterViews[i].setTextColor(Color.BLACK);
            }
        }
        if (correct) {
            // correct guess
            if (numCorr == currLen) {
                // won the game
                AlertDialog.Builder winMsg = new AlertDialog.Builder(this);
                winMsg.setTitle("YAY");
                winMsg.setMessage("You win! Congratulations!");
                winMsg.setNegativeButton("Next",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                gameManager.reLocate(HangManGameActivity.this, 2, 3);
                                HangManGameActivity.this.finish();

                            }
                        });

                winMsg.show();
            }

        } else if (remainingBallons > 0) {
            // player still has lives left

            balloons[remainingBallons - 1].setVisibility(v.INVISIBLE);
            remainingBallons = remainingBallons - 1;

            if (remainingBallons == 0) {
                AlertDialog.Builder loseMsg = new AlertDialog.Builder(this);
                System.out.println(20);
                loseMsg.setTitle("OOPS :(");
                loseMsg.setMessage("You lose!");
                loseMsg.setNegativeButton("Try again",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                gameManager.reLocate(HangManGameActivity.this, 0, 0);
                                HangManGameActivity.this.finish();
                            }
                        });

                loseMsg.show();
            }


        }
    }

    private void playHangMan() {
        // plays a new HangMan game
        // String newWord = words[rand.nextInt(words.length)];
        currentWord = "BULLETPROOF";
        characterViews = new TextView[currentWord.length()];
        wordLayout.removeAllViews();


//        for(int p = 0; p < balloons.length; p++) {
//            balloons[p].setVisibility(View.VISIBLE);
//        }


        for (int c = 0; c < currentWord.length(); c++) {
            characterViews[c] = new TextView(this);
            characterViews[c].setText("" + currentWord.charAt(c));
        }

        remainingBallons = 6;
        currLen = currentWord.length();
        numCorr = 0;

        for (int c = 0; c < currentWord.length(); c++) {
            characterViews[c] = new TextView(this);
            characterViews[c].setText("" + currentWord.charAt(c));
            characterViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            characterViews[c].setGravity(Gravity.CENTER);
            characterViews[c].setTextColor(Color.WHITE);
            characterViews[c].setBackgroundResource(R.drawable.hm_letter_background);
            //add to layout
            wordLayout.addView(characterViews[c]);
        }

    }

}
