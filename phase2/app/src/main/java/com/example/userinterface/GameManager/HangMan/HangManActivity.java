package com.example.userinterface.GameManager.HangMan;

import android.app.Activity;
import android.os.Bundle;

import com.example.userinterface.R;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HangManActivity extends Activity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // refer to the hang_man_main layout
        setContentView(R.layout.hang_man_main);

        // the play button that controls if the game will start
        Button playBtn = findViewById(R.id.playButton);
        playBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // handles clicks
        if (v.getId() == R.id.playButton) {
            Intent intent = new Intent(HangManActivity.this, HangManGameActivity.class);
            startActivity(intent);
        }
    }
}
