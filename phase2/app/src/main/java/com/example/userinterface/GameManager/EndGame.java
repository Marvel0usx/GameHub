package com.example.userinterface.GameManager;

import android.os.Bundle;
import android.view.View;

import com.example.userinterface.R;

/**
 * The end of the game screen.
 */
public class EndGame extends GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
    }

    /**
     * ending the game and saving the users score and level to the server.
     * @param view
     */
    public void endgame(View view) {
        StatTracker statTracker = getUser().getStatTracker();
        statTracker.addNumOfGames();
        if (statTracker.getCurrScore() > statTracker.getHighScore()) {
            statTracker.setHighScoreToCurrent();
        }
        GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity();
        getUser().getStatTracker().setCurrScore(0);
        gameBackgroundActivity.execute("stats", getUser());
        getUser().getStatTracker().setLevel(0);
        toMenu();
    }
}
