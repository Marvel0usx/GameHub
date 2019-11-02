package com.example.userinterface.GameManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userinterface.R;

public class NextGame extends AppCompatActivity {
    Games games;
    TextView textView;
    boolean won;
    Button btnNext;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_game);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        textView = findViewById(R.id.winOrLose);
        btnNext = findViewById(R.id.next);
        if (bundle != null) {
            games = (Games) bundle.getSerializable("Game");
            won = bundle.getBoolean("won");
        }
        if (won) {
            textView.setText("You Have Won");
        } else {
            textView.setText("You Have Lost");
            btnNext.setEnabled(false);

        }
    }

    public void quit(View view) {
        games.toMenu(NextGame.this);
    }

    public void next(View view) {
        games.next(NextGame.this);
    }
}
