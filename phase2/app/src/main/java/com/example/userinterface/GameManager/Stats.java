package com.example.userinterface.GameManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.userinterface.R;

/**
 * The users stat
 */
public class Stats extends GameActivity {
    TextView userLevel, highScore, gamesPlayed, currentScore;
    User user;

    /**
     * Get all the user statistics and set it on the screen.
     *
     * @param savedInstanceState saved instance state
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        user = getUser();
        setContentView(R.layout.activity_stats);
        userLevel = findViewById(R.id.userLevel);
        highScore = findViewById(R.id.highScore);
        currentScore = findViewById(R.id.currentScore);
        gamesPlayed = findViewById(R.id.gamesPlayed);
        userLevel.setText(user.getStatTracker().getLevel() + "");
        highScore.setText(user.getStatTracker().getHighScore() + "");
        gamesPlayed.setText(user.getStatTracker().getNumOfGames() + "");
        currentScore.setText(user.getStatTracker().getCurrScore() + "");
        setBadgesButton();
    }

    /**
     * The badge button that goes to the badges page.
     */
    protected void setBadgesButton() {
        findViewById(R.id.BadgesButton).setOnClickListener(v -> {
                    Intent intent = new Intent(Stats.this,
                            DisplayBadgesActivity.class);
                    startActivity(intent);
                }
        );
    }

}
