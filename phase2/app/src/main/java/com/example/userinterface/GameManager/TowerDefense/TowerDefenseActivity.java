package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.userinterface.GameManager.*;
import com.example.userinterface.R;

public class TowerDefenseActivity extends GameActivity implements TowerDefenseView, BadgeCollector {

    Button btnStart, btnTower1, btnTower2, btnTower3;
    TowerDefensePresenter towerDefensePresenter;
    TowerDefense towerDefense;
    int width;
    int height;
    GameView gameView;
    TowerPositions towerPositions;
    Button[] buttonTowers;
    boolean practiceMode;
    TextView money;
    Button selectedTower;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tower_defense);
        context = this;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            practiceMode = bundle.getBoolean("practice");
        } else {
            practiceMode = false;
        }

        DisplayMetrics metrics = new DisplayMetrics(); // find size of the screen
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        gameView = findViewById(R.id.myView);
        money = findViewById(R.id.money);
        towerDefensePresenter = new TowerDefensePresenter(this);
        Log.d("message", "towerDefensePresenter at line 62 " + towerDefensePresenter);
        width = size.x;
        height = size.y;
        TowerPositions.setHeight(height);
        TowerPositions.setWidth(width);
        towerDefense = new TowerDefense(width, height, towerDefensePresenter);
        Log.d("message", "towerDefensePresenter at line 68" + towerDefensePresenter);
        towerDefensePresenter.setTowerDefense(towerDefense);
    }

    public void onBackPressed() {
        gameView.setGameOver(true);
        gameView.getThread().setRunning(false);
        toMenu();
    }

    public void towerClick(View view) {
        selectedTower = (Button) view;
        if (enoughMoney()) {
            for (Button button : buttonTowers) {
                button.setEnabled(false);
            }
            towerDefensePresenter.towerClick();
        }

    }
    public void showTowerPositions(){
        towerPositions.showAvailable(true);
    }

    public boolean enoughMoney() {
        int cost = Integer.parseInt(selectedTower.getContentDescription().toString().split(" ")[1]);
        return towerDefensePresenter.enoughMoney(cost);
    }

    public void setTower(View view) {
        if (towerPositions.isTowerClicked()) {
            Button tower = selectedTower;
            view.setEnabled(false);
            int index = towerPositions.getTowerNumber((Button) view);
            if (tower == btnTower1) {
                view.setBackgroundResource(R.drawable.towercopy);
            } else if (tower == btnTower2) {
                view.setBackgroundResource(R.drawable.tower2copy);
            } else if (tower == btnTower3) {
                view.setBackgroundResource(R.drawable.tower3copy);
            }
            String [] name = selectedTower.getContentDescription().toString().split(" ");
            towerDefensePresenter.setTower((int)view.getX(), (int)view.getY(),name[0],index);
            for (Button button : buttonTowers) {
                button.setEnabled(true);
            }
            towerPositions.showAvailable(false);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        btnStart = findViewById(R.id.start);
        btnTower1 = findViewById(R.id.tower1);
        btnTower2 = findViewById(R.id.tower2);
        btnTower3 = findViewById(R.id.tower3);
        buttonTowers = new Button[]{btnTower1, btnTower2, btnTower3};

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
        Button[] buttons = {button, button1, button2, button3, button4, button5, button6, button7, button8, button9};
        towerPositions = new TowerPositions(buttons);
        towerPositions.setXLocation();
        towerPositions.setYLocation();

        if (gameView != null) {
            gameView.setTowerDefensePresenter(towerDefensePresenter);
            gameView.setGameStart(true);
        }
    }

    public void onStartClick(View v) {
        Log.d("message", "towerDefensePresenter at line 170 " + towerDefensePresenter);
        int gamePlayed = getUser().getStatTracker().getNumOfGames();
        towerDefensePresenter.onStartClicked(gamePlayed);
    }

    public void setButtonVisible(){
        btnStart.setVisibility(View.GONE);
        btnTower1.setVisibility(View.VISIBLE);
        btnTower2.setVisibility(View.VISIBLE);
        btnTower3.setVisibility(View.VISIBLE);
    }

    @Override
    public void endGame(boolean won, int score) {
        gameView.setGameOver(true);
        gameView.getThread().setRunning(false);
        if (!practiceMode)
            if (won)
                getUser().getStatTracker().addToCurrScore(score);
                getUser().getStatTracker().addToBadges(collectFortunateBadge(),
                                                        collectStrategicBadge(),
                                                        collectAdventurousBadge());
        goToIntermediate(won, practiceMode);
        // record score of the level Intermediate page between games
    }

    @Override
    public boolean collectFortunateBadge() {
        return true;
    }

    @Override
    public boolean collectStrategicBadge() {
        return true;
    }

    @Override
    public boolean collectAdventurousBadge() {
        return towerDefensePresenter.getAdventurous();
    }
}




