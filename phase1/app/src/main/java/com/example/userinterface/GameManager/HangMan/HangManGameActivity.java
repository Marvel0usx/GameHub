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

import com.example.userinterface.GameManager.Games;
import com.example.userinterface.R;


public class HangManGameActivity extends Activity {
    protected Games gameManager;
    private GameState gameState;
    private int currentScore;

    private int numLives;
    private ImageView[] balloons;

    private AnswerKeyLetter[] answerKey;
    private TextView[] characterViews;
    private LinearLayout wordLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_activity_game);
        wordLayout = findViewById(R.id.word);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            gameManager = (Games) bundle.getSerializable("Game");
        }
        balloons = new ImageView[numLives];
        balloons[0] = findViewById(R.id.ballon1); // the part right of equal sign is an image view
        Balloon balloon_0 = new Balloon(balloons[0]);
        balloons[1] = findViewById(R.id.ballon2);
        Balloon balloon_1 = new Balloon(balloons[1]);
        balloons[2] = findViewById(R.id.ballon3);
        Balloon balloon_2 = new Balloon(balloons[2]);
        balloons[3] = findViewById(R.id.ballon4);
        Balloon balloon_3 = new Balloon(balloons[3]);
        balloons[4] = findViewById(R.id.ballon5);
        Balloon balloon_4 = new Balloon(balloons[4]);
        balloons[5] = findViewById(R.id.ballon6);
        Balloon balloon_5 = new Balloon(balloons[5]);
        Balloon[] tempBalloons = {balloon_0, balloon_1, balloon_2, balloon_3, balloon_4, balloon_5};
        //List<Balloon> tempBalloons = new ArrayList<Balloon>();

        gameState = new GameState("BULLETPROOF", tempBalloons);

        wordLayout.removeAllViews();
        String keyword = gameState.getKeyWord();
        answerKey = new AnswerKeyLetter[keyword.length()]; // an array of all answerkey letters (correct)
        for (int c = 0; c < keyword.length(); c++){
            answerKey[c] = new AnswerKeyLetter(keyword.charAt(c)); // makes a new AnswerKeyLetter for each iteration
            characterViews[c] = new TextView(this); // creates a text view
            characterViews[c].setText("" + keyword.charAt(c));
            characterViews[c].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            characterViews[c].setGravity(Gravity.CENTER);
            characterViews[c].setTextColor(Color.WHITE); // set it to be white
            characterViews[c].setBackgroundResource(R.drawable.hm_letter_background);
            answerKey[c].addTextView(characterViews[c]);
            wordLayout.addView(answerKey[c].getTextView());
        }


    }
    // don't need playHangMan method anymore; can directly use the buttons
    public void makeGuess(View v){
        String letterGuessed = ((TextView) v).getText().toString();
        char charGuessed = letterGuessed.charAt(0);
        v.setEnabled(false);
        v.setBackgroundResource(R.drawable.hm_letter_clicked);

        this.gameState.updateState(charGuessed);
        this.currentScore = gameState.getCurrentScore();
        endGame();
    }

    public void endGame(){
        if (gameState.numCorr == gameState.keywordLen) {
            this.currentScore += 100;
            gameManager.getUser().addToCurrScore(this.currentScore);
            gameManager.toInter(HangManGameActivity.this,true);
            HangManGameActivity.this.finish();
        } else if (gameState.remainingBalloons == 0){
            gameManager.getUser().addToCurrScore(this.currentScore);
            gameManager.toInter(HangManGameActivity.this,false);
            // game ends because all lives have been used up
            HangManGameActivity.this.finish();
        }
    }


    @Override
    public void onBackPressed() {
        gameManager.reLocate(HangManGameActivity.this, 1, 0);
    }

}
