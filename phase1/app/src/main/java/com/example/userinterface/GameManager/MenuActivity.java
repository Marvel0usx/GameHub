package com.example.userinterface.GameManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.userinterface.GameManager.HangMan.HangManActivity;
import com.example.userinterface.R;

public class MenuActivity extends AppCompatActivity {

    Button btnStart;
    Button btnResume;
    Button btnStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnStart = findViewById(R.id.start);
        btnResume = findViewById(R.id.Resume);
        btnStats = findViewById(R.id.Stats);
    }

    public void startGame(View v){
        Intent intent = new Intent(MenuActivity.this, HangManActivity.class);
        startActivity(intent);
    }
    public void Resume(View v){
        Intent intent = new Intent(MenuActivity.this, HangManActivity.class);
        startActivity(intent);
    }

    public void Stats(View view) {
        Intent intent = new Intent(MenuActivity.this, Stats.class);
        startActivity(intent);
    }
}
