package com.example.userinterface.GameManager.TowerDefense;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.userinterface.GameManager.*;
import com.example.userinterface.R;

public class TowerDefenseActivity extends GameActivity implements VariableChangeListener {

    Button btnStart, btnHit, btnTower1, btnTower2, btnTower3;
    TowerDefense towerDefense;
    int width;
    int height;
    GameView gameView;
    TowerPositions towerPositions;
    Button [] towers;


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
        TowerPositions.height = height;
        TowerPositions.width = width;
        towerDefense = new TowerDefense(width, height);
        towerDefense.setVariableChangeListener(this); //Activity listens to TowerDefense's variable change

        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button2);
        Button button2 = findViewById(R.id.button3);
        Button button3 = findViewById(R.id.button4);
        Button button4 = findViewById(R.id.button5);
        Button button5 = findViewById(R.id.button6);
        Button button6 = findViewById(R.id.button7);
        Button button7 = findViewById(R.id.button8);
        Button button8 = findViewById(R.id.button9);
        Button button9 = findViewById(R.id.button10);
        Button[] buttons = {button, button1,button2,button3,button4,button5,button6,button7,button8,button9};
        towerPositions = new TowerPositions(buttons);
        towerPositions.setXLocation();
        towerPositions.setYLocation();

    }

    public void onBackPressed(){
        gameView.setGameOver(true);
        gameView.getThread().setRunning(false);
        toMenu();
    }

    public void towerClick(View view){
        for (Button button: towers){
            if (button !=  view){
                button.setEnabled(false);
            }
        }
        towerPositions.showAvailable(true);
    }

    public void setTower(View view){
        if (towerPositions.isTowerClicked()) {
            Button tower = null;
            for (Button button : towers) {
                if (button.isEnabled()) {
                    tower = button;
                }
            }
            if (tower != null) {
                view.setEnabled(false);
                if (tower == btnTower1) {
                    view.setBackgroundResource(R.drawable.towercopy);
                } else if (tower == btnTower2) {
                    view.setBackgroundResource(R.drawable.tower2copy);
                } else if (tower == btnTower3) {
                    view.setBackgroundResource(R.drawable.tower3copy);
                }
            }
            for (Button button : towers) {
                if (button != tower) {
                    button.setEnabled(true);
                }
            }
            towerPositions.showAvailable(false);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        btnStart = findViewById(R.id.start);
        btnHit = findViewById(R.id.hit);
        btnTower1 = findViewById(R.id.tower1);
        btnTower2 = findViewById(R.id.tower2);
        btnTower3 = findViewById(R.id.tower3);
        towers = new Button[]{btnTower1,btnTower2,btnTower3};
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




