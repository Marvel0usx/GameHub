package com.example.userinterface.GameManager.HangMan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.userinterface.GameManager.Games;
import com.example.userinterface.R;


public class NewActivity extends Activity {
    private Games gameManager;
    private int remainingBalloons;
    private ImageView[] balloons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_activity_game);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            gameManager = (Games) bundle.getSerializable("Game");
        }

        balloons[0] = findViewById(R.id.ballon1); // the part right of equal sign is an image view
        balloons[1] = findViewById(R.id.ballon2);
        balloons[2] = findViewById(R.id.ballon3);
        balloons[3] = findViewById(R.id.ballon4);
        balloons[4] = findViewById(R.id.ballon5);
        balloons[5] = findViewById(R.id.ballon6);
    }
}
