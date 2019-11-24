package com.example.userinterface.GameManager.HangMan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.userinterface.GameManager.GameActivity;
import com.example.userinterface.GameManager.User;
import com.example.userinterface.R;



public class HangManGameActivity extends GameActivity {
    private GameState gameState;
    private int currentScore;
    ImageView[] balloons;
    Difficulty difficulty;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_activity_gameee);
        LinearLayout wordLayout = findViewById(R.id.word);
        Intent intent = getIntent();
        user = getUser();

        difficulty = (Difficulty) intent.getSerializableExtra("difficulty");
        difficulty.setWord();
        difficulty.setNumLives();

        // creates an array list of ImageView objects, each associated with a balloon image
        // that gets displayed
        // initialize each Balloon object
        Balloon[] tempBalloons = loadBalloons();

        // initialize a new GameState object for this round
        gameState = new GameState(difficulty);
        gameState.setBalloons(tempBalloons);
        gameState.setKeyword(difficulty.keyword);
        gameState.setRemainingBalloons(difficulty.numLives);
        wordLayout.removeAllViews();
        String keyword = gameState.getKeyWord();
        // an array that stores all letters of the correct word
        AnswerKeyLetter[] answerKey = new AnswerKeyLetter[keyword.length()];
        // each letter of the correct word is represented as a TextView object

        TextView[] characterViews = new TextView[keyword.length()];
        for (int c = 0; c < keyword.length(); c++) {
            answerKey[c] = new AnswerKeyLetter(keyword.charAt(c)); // makes a new AnswerKeyLetter
            characterViews[c] = new TextView(this); // creates a TextView object
            characterViews[c].setText("" + keyword.charAt(c));
            characterViews[c].setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            characterViews[c].setGravity(Gravity.CENTER);
            // set it to be white so it does not show up against the white background
            characterViews[c].setTextColor(Color.WHITE);
            characterViews[c].setBackgroundResource(R.drawable.hm_letter_background);
            answerKey[c].addTextView(characterViews[c]);
            wordLayout.addView(answerKey[c].getTextView());
        }

        gameState.setAnswerKeys(answerKey);
    }

    /**
     * Guesses a new letter
     *
     * @param v View object
     */
    public void makeGuess(View v) {
        String letterGuessed = ((TextView) v).getText().toString();
        char charGuessed = letterGuessed.charAt(0);
        v.setEnabled(false);
        v.setBackgroundResource(R.drawable.hm_letter_clicked);
        // updates the gameState by calling the updateState method
        this.gameState.updateState(charGuessed);
        this.currentScore = gameState.getCurrentScore();
        endGame();
    }

    /**
     * Based on whether all letters habve been guessed, decides whether the game is won. If won,
     * currentScore is updated. This game is finished.
     */
    public void endGame() {
        if (gameState.numCorr == gameState.keywordLen) {
            // if all letters have been guessed, wins the game
            this.currentScore += 100;
            user.getStatTracker().addToCurrScore(currentScore);
            goToIntermediate(true);
            HangManGameActivity.this.finish();
        } else if (gameState.remainingBalloons == 0) {
            // loses the game if all lives are used up/balloons have disappeared.
            goToIntermediate(false);
            HangManGameActivity.this.finish();
        }
    }

    public Balloon[] loadBalloons(){
        int numLives = difficulty.getNumLives();
        balloons = new ImageView[numLives];
        Balloon[] temp = new Balloon[numLives];
        for (int i = 0; i < numLives; i++) {
            String balloonId = "ballon" + (i + 1);
            int resID = getResources().getIdentifier(balloonId, "id", getPackageName());
            balloons[i] = ((ImageView) findViewById(resID));
            temp[i] = new Balloon(balloons[i]);
        }
        return temp;
    }


}
