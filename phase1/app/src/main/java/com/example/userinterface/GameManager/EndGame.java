package com.example.userinterface.GameManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.userinterface.R;

public class EndGame extends AppCompatActivity {
    GameManager gameManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){
            gameManager =(GameManager) bundle.getSerializable("Game");
        }
    }

    public void endgame(View view){
        gameManager.getUser().getStatTracker().setLevel(0);
        gameManager.toMenu(this);
    }
}
