package com.example.userinterface.GameManager.TowerDefense;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.userinterface.GameManager.*;
import com.example.userinterface.GameManager.GameBackgroundActivity;
import com.example.userinterface.R;

import java.io.Serializable;
import java.util.ArrayList;

public class TowerDefenseActivity extends GameActivity implements VariableChangeListener {

    Button btnStart, btnHit, btnTower1, btnTower2, btnTower3;
    TowerDefense towerDefense;
    int width;
    int height;
    Games gameManager;
    GameView gameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tower_defense);
        context = this;
        DisplayMetrics metrics = new DisplayMetrics(); // find size of the screen
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        gameView = findViewById(R.id.myView);
        width = size.x;
        height = size.y;
        towerDefense = new TowerDefense(width, height);
        towerDefense.setVariableChangeListener(this); //Activity listens to TowerDefense's variable change
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        btnStart = findViewById(R.id.start);
        btnHit = findViewById(R.id.hit);
        btnTower1 = findViewById(R.id.tower1);
        btnTower2 = findViewById(R.id.tower2);
        btnTower3 = findViewById(R.id.tower3);
        if (gameView!=null){
            gameView.setTowerDefense(towerDefense);
        }
    }

    public void onClick(View v) {
        System.out.println("hahahahaha");
        switch (v.getId()) {
            case R.id.start: //hit button and towers become visible after pressing startbutton
                btnHit.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);
                towerDefense.addEnemy();
                System.out.println("fsdadfsa");
                btnTower1.setVisibility(View.VISIBLE);
                btnTower2.setVisibility(View.VISIBLE);
                btnTower3.setVisibility(View.VISIBLE);
                gameView.setGameStart(true);
                break;

            case R.id.hit:
                towerDefense.clicked(); //record one click to hit the enemy
                break;

            default:
                break;
        }
    }

    @Override
    public void onVariableChange(boolean gameOver) {
        gameView.setGameOver(true);
        gameView.getThread().setRunning(false);
        getUser().getStatTracker().addToCurrScore(towerDefense.getGameScore());
        goToIntermediate(gameOver);
        // record score of the level Intermediate page between games
    }
}



