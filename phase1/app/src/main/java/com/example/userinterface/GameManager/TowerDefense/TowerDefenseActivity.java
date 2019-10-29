package com.example.userinterface.GameManager.TowerDefense;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.userinterface.GameManager.*;
import com.example.userinterface.GameManager.GameBackgroundActivity;
import com.example.userinterface.R;

import java.util.ArrayList;

public class TowerDefenseActivity extends Activity implements VariableChangeListener {

    Button btnStart, btnHit, btnTower1, btnTower2, btnTower3;
    TowerDefense towerDefense;
    int width;
    int height;
    User user;


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
        towerDefense.setVariableChangeListener(this); //Activity listens to TowerDefense's variable change
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            user = (User)bundle.getSerializable("User");
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
        GameView gameView = findViewById(R.id.myView);
        if(gameView != null) {
            gameView.setTowerDefense(towerDefense);
        }
    }

    public void onBackPressed(){
        super.onBackPressed();
        GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity(TowerDefenseActivity.this);
        gameBackgroundActivity.execute("quit", user, 1);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                //btnHit.setVisibility(View.VISIBLE);
                btnTower1.setVisibility(View.VISIBLE);
                btnTower2.setVisibility(View.VISIBLE);
                btnTower3.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);
                towerDefense.addEnemy();
                break;

            case R.id.hit:
                towerDefense.clicked();
                break;

            default:
                break;
        }
    }

    @Override
    public void onVariableChange(boolean gameOver) {


//        boolean win = towerDefense.getWin();
//        AlertDialog.Builder db = new AlertDialog.Builder(this);
//        db.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//               finish();
//            }
//        });
//
//        if (!win) {
//            db.setMessage("You have LOST:(!");
//        }
//        else{
//            db.setMessage("Congratulations! You have WON!!");
//        }
//
//
//    }
    }
}





