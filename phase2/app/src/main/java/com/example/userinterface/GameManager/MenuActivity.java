package com.example.userinterface.GameManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.userinterface.R;

public class MenuActivity extends GameActivity {
    /**
     * This is the main menu for the game, with a few buttons that enable the user to start a game
     * or resume a game or see his or her scoreboard.
     */
    Button btnStart;
    Button btnResume;
    Button btnStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        setContentView(R.layout.activity_menu);
        btnStart = findViewById(R.id.start);
        btnResume = findViewById(R.id.Resume);
        btnStats = findViewById(R.id.Stats);
        if (getUser().getStatTracker().getLevel()==0){
            btnResume.setEnabled(false);
        }else{
            btnResume.setEnabled(true);
        }

        super.onCreate(savedInstanceState);


        /**
         * Starting a new game
         */
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUser().getStatTracker().setCurrScore(0);
                toGame(1);
            }
        });
        /**
         * Resuming a game if possible
         */
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getUser().getStatTracker().getLevel()){
                    case 1:
                        toGame(1);
                        break;
                    case 2:
                        toGame(2);
                        break;
                    case 3:
                        toGame(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * Go to the stats board
     * @param view
     */
    public void Stats(View view) {
        Intent intent = new Intent(MenuActivity.this, Stats.class);
        startActivity(intent);
    }
}
