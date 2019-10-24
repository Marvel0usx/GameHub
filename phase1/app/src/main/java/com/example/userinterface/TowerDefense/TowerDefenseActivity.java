package com.example.userinterface.TowerDefense;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.userinterface.R;

public class TowerDefenseActivity extends Activity {

    Button btnStart, btnHit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tower_defense);
        btnStart = findViewById(R.id.start);
        btnHit = findViewById(R.id.hit);
        btnHit.setVisibility(View.GONE);
    }

    public void click(View v){
        GameView.temp = "HELLO";
        TowerDefense.clicker++;
    }

    public void start(View view){
        btnHit.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.GONE);
        btnHit.setX(200);
        TowerDefense.addEnemy();
    }
}



