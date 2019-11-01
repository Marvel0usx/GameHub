package com.example.userinterface.GameManager.HangMan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.userinterface.GameManager.Games;
import com.example.userinterface.R;


public class NewActivity extends Activity {
    private Games gameManager;
    private GameState gameState;

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
        balloons[1] = findViewById(R.id.ballon2);
        balloons[2] = findViewById(R.id.ballon3);
        balloons[3] = findViewById(R.id.ballon4);
        balloons[4] = findViewById(R.id.ballon5);
        balloons[5] = findViewById(R.id.ballon6);

        wordLayout.removeAllViews();
        String currentWord = gameState.getCurrentWord();
        answerKey = new AnswerKeyLetter[currentWord.length()];
        for (int c = 0; c < currentWord.length(); c++){
            answerKey[c] = new AnswerKeyLetter(currentWord.charAt(c));
            characterViews[c] = new TextView(this);
            characterViews[c].setText("" + currentWord.charAt(c));
            characterViews[c].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            characterViews[c].setGravity(Gravity.CENTER);
            characterViews[c].setTextColor(Color.WHITE);
            characterViews[c].setBackgroundResource(R.drawable.hm_letter_background);
            answerKey[c].addTextView(characterViews[c]);
            wordLayout.addView(answerKey[c].getTextView());
        }

    }

    @Override
    public void onBackPressed() {
        gameManager.reLocate(NewActivity.this, 1, 0);
    }

}
