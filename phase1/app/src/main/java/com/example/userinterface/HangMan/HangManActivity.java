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

    HangManManager hangManManager;

    public static void main(String[] args) {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hang_man_main);
        Button playBtn = (Button)findViewById(R.id.playBtn);
        playBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // handles clicks
        if (v.getId() == R.id.playBtn) {
            Intent playIntent = new Intent(this, HangManGameActivity.class);
            this.startActivity(playIntent);
        }
    }
}
