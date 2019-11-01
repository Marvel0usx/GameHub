package com.example.userinterface.GameManager.HangMan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.userinterface.GameManager.Games;
import com.example.userinterface.R;


public class NewActivity extends Activity {
    private Games gameManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_activity_game);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            gameManager = (Games) bundle.getSerializable("Game");
        }
    }
}
