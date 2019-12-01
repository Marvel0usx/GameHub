package com.example.userinterface.GameManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.userinterface.R;

/**
 * This is the main menu for the game, with a few buttons that enable the user to start a game
 * or resume a game or see his or her scoreboard.
 */
public class MenuActivity extends GameActivity {

    Button btnStart;
    Button btnResume;
    Button btnStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnStart = findViewById(R.id.start);
        btnResume = findViewById(R.id.Resume);
        btnStats = findViewById(R.id.Stats);
        if (getUser().getStatTracker().getLevel() == 0) {
            btnResume.setEnabled(false);
        } else {
            btnResume.setEnabled(true);
        }



        /*
         * The onclick listener that starts a new game
         */
        btnStart.setOnClickListener(v -> {
            getUser().getStatTracker().setCurrScore(0);
            toGame(1, false);
        });
        /*
         * The onClick listener that resumes a game if possible
         */
        btnResume.setOnClickListener(v -> {
            switch (getUser().getStatTracker().getLevel()) {
                case 1:
                    toGame(1, false);
                    break;
                case 2:
                    toGame(2, false);
                    break;
                case 3:
                    toGame(3, false);
                    break;
                case 4:
                    toGame(4, false);
                default:
                    break;
            }
        });
    }

    public void toScoreBoard(View view) {
        Intent intent = new Intent(MenuActivity.this, ScoreBoard.class);
        startActivity(intent);
    }

    /**
     * Go to the stats board
     *
     * @param view the button that is clicked.
     */
    public void goToStatsheet(View view) {
        Intent intent = new Intent(MenuActivity.this, Stats.class);
        startActivity(intent);
    }

    /**
     * This is the practice mode on click, where it leads to the practice mode activity.
     *
     * @param v The practice button
     */
    public void practiceMode(View v) {
        Intent intent = new Intent(MenuActivity.this, PracticeMode.class);
        startActivity(intent);
    }

}
