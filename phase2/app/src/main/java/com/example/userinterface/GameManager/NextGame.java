package com.example.userinterface.GameManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.userinterface.R;

public class NextGame extends GameActivity {
    Games games;
    TextView textView;
    boolean won;
    Button btnNext;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_game);
        textView = findViewById(R.id.winOrLose);
        btnNext = findViewById(R.id.next);
        if (!isIfLost()) {
            textView.setText("You Have Won");
        } else {
            textView.setText("You Have Lost");
            btnNext.setEnabled(false);

        }
    }

    public void quit(View view) {
        toMenu();
    }


}
