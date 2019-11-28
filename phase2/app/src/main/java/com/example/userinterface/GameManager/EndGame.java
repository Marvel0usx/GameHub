package com.example.userinterface.GameManager;

import android.os.Bundle;
import android.view.View;

import com.example.userinterface.R;

public class EndGame extends GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
    }

    public void endgame(View view) {
        StatTracker statTracker = getUser().getStatTracker();
        statTracker.addNumOfGames();
        if (statTracker.getCurrScore() > statTracker.getHighScore()) {
            statTracker.setHighScoreToCurrent();
        }
        GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity(this);
        getUser().getStatTracker().setCurrScore(0);
        gameBackgroundActivity.execute("stats", getUser());
        getUser().getStatTracker().setLevel(0);
        toMenu();
    }
}
