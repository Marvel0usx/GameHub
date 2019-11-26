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
import com.example.userinterface.GameManager.TowerDefense.Towers.BombTower;
import com.example.userinterface.GameManager.TowerDefense.Towers.GunTower;
import com.example.userinterface.GameManager.TowerDefense.Towers.RocketTower;
import com.example.userinterface.GameManager.TowerDefense.Towers.TowerFactory;
import com.example.userinterface.GameManager.TowerDefense.Towers.Towers;
import com.example.userinterface.R;

public class TowerDefenseActivity extends GameActivity implements VariableChangeListener {

    Button btnStart, btnTower1, btnTower2, btnTower3;
    TowerDefense towerDefense;
    int width;
    int height;
    GameView gameView;
    TowerPositions towerPositions;
    Button[] buttonTowers;
    boolean practiceMode;
    TextView money;


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
        width = size.x;
        height = size.y;
        TowerPositions.height = height;
        TowerPositions.width = width;
        towerDefense = new TowerDefense(width, height);
        towerDefense.setVariableChangeListener(this); //Activity listens to TowerDefense's variable change
        money = findViewById(R.id.money);

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

    }

    public void onBackPressed() {
        gameView.setGameOver(true);
        gameView.getThread().setRunning(false);
        toMenu();
    }

    public void towerClick(View view) {
        if (enoughMoney(view)) {
            for (Button button : buttonTowers) {
                if (button != view) {
                    button.setEnabled(false);
                }
            }
            towerPositions.showAvailable(true);
        }

    }

    public boolean enoughMoney(View v) {
        int money = towerDefense.getCash();
        if (v == btnTower1) {
            if (money >= GunTower.COST) {
                towerDefense.costMoney(GunTower.COST);
                return true;
            }
        } else if (v == btnTower2) {
            if (money >= RocketTower.COST) {
                towerDefense.costMoney(RocketTower.COST);
                return true;
            }
        } else {
            if (money >= BombTower.COST) {
                towerDefense.costMoney(BombTower.COST);
                return true;
            }
        }
        return false;
    }

    public void setTower(View view) {
        TowerFactory towerFactory = new TowerFactory();
        if (towerPositions.isTowerClicked()) {
            Button tower = null;
            for (Button button : buttonTowers) {
                if (button.isEnabled()) {
                    tower = button;
                }
            }
            if (tower != null) {
                view.setEnabled(false);
                int index = towerPositions.getTowerNumber((Button) view);
                String side;
                if (index % 2 == 0) {
                    side = "left";
                } else {
                    side = "right";
                }
                if (tower == btnTower1) {
                    view.setBackgroundResource(R.drawable.towercopy);
                    Towers temp = towerFactory.buildTower("guntower", side);
                    temp.setLocation((int) view.getX(), (int) view.getY());
                    towerDefense.addTower(index, temp);
                } else if (tower == btnTower2) {
                    view.setBackgroundResource(R.drawable.tower2copy);
                    Towers temp = towerFactory.buildTower("rockettower", side);
                    temp.setLocation((int) view.getX(), (int) view.getY());
                    towerDefense.addTower(index, temp);
                } else if (tower == btnTower3) {
                    view.setBackgroundResource(R.drawable.tower3copy);
                    Towers temp = towerFactory.buildTower("bombtower", side);
                    temp.setLocation((int) view.getX(), (int) view.getY());
                    towerDefense.addTower(index, temp);
                }
            }
            for (Button button : buttonTowers) {
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
        btnTower1 = findViewById(R.id.tower1);
        btnTower2 = findViewById(R.id.tower2);
        btnTower3 = findViewById(R.id.tower3);
        buttonTowers = new Button[]{btnTower1, btnTower2, btnTower3};
        if (gameView != null) {
            gameView.setTowerDefense(towerDefense);
            gameView.setGameStart(true);
        }
    }

    public void onClick(View v) {
        btnStart.setVisibility(View.GONE);
        towerDefense.addEnemy();
        btnTower1.setVisibility(View.VISIBLE);
        btnTower2.setVisibility(View.VISIBLE);
        btnTower3.setVisibility(View.VISIBLE);
        towerDefense.setGameStart(true);

    }

    @Override
    public void onVariableChange(boolean gameOver) {
        gameView.setGameOver(true);
        gameView.getThread().setRunning(false);
        if (!practiceMode)
            if (towerDefense.getWin())
                getUser().getStatTracker().addToCurrScore(towerDefense.getGameScore());
        Log.d("message", "this is the boolean at to game in td act " + practiceMode);
        goToIntermediate(towerDefense.getWin(), practiceMode);
        // record score of the level Intermediate page between games
    }
}




