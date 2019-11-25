package com.example.userinterface.GameManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.userinterface.R;

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
            Log.d("message","this is the boolean at to game in next game "+practiceMode);

        }else
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

    public void quit(View view) {
        toMenu();
    }


}
