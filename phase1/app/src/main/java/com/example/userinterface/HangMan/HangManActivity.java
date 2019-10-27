package com.example.userinterface.HangMan;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.example.userinterface.R;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HangManActivity extends Activity implements OnClickListener{

    // activity


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
            Intent playIntent = new Intent(this, HangManGameActivity.class);
            this.startActivity(playIntent);
        }
    }
}
