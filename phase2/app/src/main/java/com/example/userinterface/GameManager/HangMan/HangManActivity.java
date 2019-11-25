package com.example.userinterface.GameManager.HangMan;

import android.app.Activity;
import android.os.Bundle;



import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.userinterface.R;

public class HangManActivity extends Activity{

    GameState gameState;
    Difficulty diff;
    boolean practiceMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // refer to the hang_man_main layout
        setContentView(R.layout.hang_man_main);

        // the play button that controls if the game will start

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            practiceMode = bundle.getBoolean("practice");
            Log.d("message", "0 " + practiceMode);
        }
        else
            practiceMode = false;

        setEasyBtn();
        setModerateBtn();
        setHardBtn();
    }

    private void setEasyBtn(){
        findViewById(R.id.easyButton).setOnClickListener(v -> {
            this.diff = new EasyDifficulty();
            Intent intent = new Intent(HangManActivity.this, HangManGameActivity.class);
            intent.putExtra("difficulty", diff);
            intent.putExtra("practice", practiceMode);
            startActivity(intent);
        });
    }

    private void setModerateBtn(){
        findViewById(R.id.moderateButton).setOnClickListener(v -> {
            this.diff = new ModerateDifficulty();
            Intent intent = new Intent(HangManActivity.this, HangManGameActivity.class);
            intent.putExtra("difficulty", diff);
            intent.putExtra("practice", practiceMode);
            startActivity(intent);
        });
    }

    private void setHardBtn(){
        findViewById(R.id.hardButton).setOnClickListener(v -> {
            this.diff = new HardDifficulty();
            Intent intent = new Intent(HangManActivity.this, HangManGameActivity.class);
            intent.putExtra("difficulty", diff);
            intent.putExtra("practice", practiceMode);
            startActivity(intent);
        });
    }





}
