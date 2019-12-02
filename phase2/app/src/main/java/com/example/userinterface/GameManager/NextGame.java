package com.example.userinterface.GameManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.userinterface.R;

/**
 * The next screen for the game, shows whether or not the user has won the game.
 * if lost the game disable the next button.
 */
public class NextGame extends GameActivity {
    TextView textView;
    boolean practiceMode;
    Button btnNext;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_game);
        textView = findViewById(R.id.winOrLose);
        btnNext = findViewById(R.id.next);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            practiceMode = bundle.getBoolean("practice");
            Log.d("message",
                    "this is the boolean at to game in next game " + practiceMode);

        } else
            practiceMode = false;

        if (!isIfLost()) {
            textView.setText("You Have Won");
            if (practiceMode)
                btnNext.setEnabled(false); //if it is practice mode there is no next game
        } else {
            textView.setText("You Have Lost");
            btnNext.setEnabled(false);
        }
    }

    /**
     * The quit button that lets the user quit to the main menu
     * @param view the button that has been clicked.
     */
    public void quit(View view) {
        toMenu();
    }

    /**
     * The onclick method to go to the next game
     * @param view that next button that is pressed,
     */
    @Override
    public void next(View view) {
        super.next(view);
    }
}
