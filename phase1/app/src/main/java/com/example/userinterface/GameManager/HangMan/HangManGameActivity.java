package com.example.userinterface.GameManager.HangMan;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;
import java.util.Random;
import com.example.userinterface.GameManager.Games;
import com.example.userinterface.GameManager.ScoreSystem;
import com.example.userinterface.R;


public class HangManGameActivity extends Activity implements ScoreSystem {
    private ImageView[] balloons; // balloon images
    private int numLives = 6; // number of lives
    private int currLen; // number of characters in current word
    private int remainingBallons; // number of balloons left, decreases each time a wrong letter is guessed
    private int numCorr; // number of letters correctly guessed
    private int currentScore;

    private String currentWord;
    private LinearLayout wordLayout;
    private TextView[] characterViews;
    private Games gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_activity_game);
        balloons = new ImageView[numLives];
        // initializes images containing different numbers of ballons, representing different number
        // of lives.
        currentWord = "";
        wordLayout = findViewById(R.id.word);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            gameManager = (Games) bundle.getSerializable("Game");
        }
        // refers each element in balloons array to the particular image view that contains each
        // possible number of balloons (the image views are already being created)
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
                currentScore += 10;
                numCorr++;
                characterViews[i].setTextColor(Color.BLACK);
            }
        }
        if (correct) {
            // correct guess
            if (numCorr == currLen) {
                // won the game
                currentScore += 100;
                gameManager.getUser().addToCurrScore(getGameScore());
                gameManager.toInter(HangManGameActivity.this,true);
                // the game ends since the user has won
                HangManGameActivity.this.finish();
            }

        } else if (remainingBallons > 0) {
            // player still has lives left
            currentScore -= 20;
            balloons[remainingBallons - 1].setVisibility(v.INVISIBLE);
            remainingBallons = remainingBallons - 1;

            if (remainingBallons == 0) {
                // updates the current score for this particular user
                gameManager.getUser().addToCurrScore(getGameScore());
                gameManager.toInter(HangManGameActivity.this,false);
                // game ends because all lives have been used up
                HangManGameActivity.this.finish();
            }
        }
    }

    private void playHangMan() {
        // plays a new HangMan game
        currentWord = "BULLETPROOF";
        /**
         * creates the view that holds the actual word that is being guessed; the number of
         * "dashes" that are at the bottom of each letter depends on the length of the word
         * sets initial  number of lives to 6, which equals number of balloons */
        characterViews = new TextView[currentWord.length()];
        wordLayout.removeAllViews();
        remainingBallons = 6;
        currLen = currentWord.length();
        numCorr = 0;

        for (int c = 0; c < currentWord.length(); c++) {
            /**
             * add the correct letters onto the screen. Since none of the letters have been guessed,
             * all the letters are being set to white and will be set to black once the correct
             * letter has been guessed. */
            characterViews[c] = new TextView(this);
            characterViews[c].setText("" + currentWord.charAt(c));
            characterViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            characterViews[c].setGravity(Gravity.CENTER);
            characterViews[c].setTextColor(Color.WHITE);
            characterViews[c].setBackgroundResource(R.drawable.hm_letter_background);
            /** add to layout */
            wordLayout.addView(characterViews[c]);
        }

    }

    @Override
    public int getGameScore() {
        return currentScore;
    }
}
