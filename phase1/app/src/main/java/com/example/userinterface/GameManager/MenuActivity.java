package com.example.userinterface.GameManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.userinterface.R;

public class MenuActivity extends Activity {
    /**
     * This is the main menu for the game, with a few buttons that enable the user to start a game
     * or resume a game or see his or her scoreboard.
     */
    Button btnStart;
    Button btnResume;
    Button btnStats;
    GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu);
        btnStart = findViewById(R.id.start);
        btnResume = findViewById(R.id.Resume);
        btnStats = findViewById(R.id.Stats);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            gameManager = (GameManager) bundle.getSerializable("Game");//Catch the information passed in
        }
        if (gameManager != null) {
            if (gameManager.getUser().getStatTracker().getLevel()==0){
                btnResume.setEnabled(false);
            }else{
                btnResume.setEnabled(true);
            }
        }
        super.onCreate(savedInstanceState);


        /**
         * Starting a new game
         */
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameManager.getUser().getStatTracker().setCurrScore(0);
                gameManager.reLocate(MenuActivity.this, 1, 1);
            }
        });
        /**
         * Resuming a game if possible
         */
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (gameManager.getUser().getStatTracker().getLevel()){
                    case 1:
                        gameManager.reLocate(MenuActivity.this,1,1);
                        break;
                    case 2:
                        gameManager.reLocate(MenuActivity.this,2,2);
                        break;
                    case 3:
                        gameManager.reLocate(MenuActivity.this,3,3);
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
        Bundle bundle = new Bundle();
        bundle.putSerializable("user",gameManager.getUser());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
