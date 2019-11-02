package com.example.userinterface.GameManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.userinterface.R;

public class Stats extends AppCompatActivity {
    TextView userLevel, highScore, gamesPlayed, currentScore;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            user = (User) bundle.getSerializable("user");
        }
        setContentView(R.layout.activity_stats);
        userLevel = findViewById(R.id.userlevel);
        highScore = findViewById(R.id.highscore);
        currentScore = findViewById(R.id.currentScore);
        gamesPlayed = findViewById(R.id.gamesplayed);
        userLevel.setText(user.getLevel() + "");
        highScore.setText(user.getHighScore() + "");
        gamesPlayed.setText(user.getNumOfGames() + "");
        currentScore.setText(user.getCurrScore() + "");
    }

}
