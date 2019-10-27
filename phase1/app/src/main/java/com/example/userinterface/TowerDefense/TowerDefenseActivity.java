package com.example.userinterface.TowerDefense;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.userinterface.R;

import java.util.ArrayList;

public class TowerDefenseActivity extends Activity {

    Button btnStart, btnHit;
    TowerDefense towerDefense;
    int width;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tower_defense);

        DisplayMetrics metrics = new DisplayMetrics(); // find size of the screen
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        towerDefense = new TowerDefense(width, height);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        btnStart = findViewById(R.id.start);
        btnHit = findViewById(R.id.hit);
        GameView gameView = findViewById(R.id.myView);
        if(gameView != null) {
            gameView.setTowerDefense(towerDefense);
        }
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                btnHit.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);
                towerDefense.addEnemy();
                break;

            case R.id.hit:
                towerDefense.setClicker(1);
                break;

            case R.id.finish: {
                int lives = towerDefense.getLives();
                ArrayList<Enemy> enemies = towerDefense.getWave1(); // change to wave3 when implemented
                if (lives == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("You have LOST:(!");
                }
                if (enemies.isEmpty() && lives > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Congratulations! You have WON!!");
                }
                finish();
                break;
            }
            default:
                break;

        }
    }
}





