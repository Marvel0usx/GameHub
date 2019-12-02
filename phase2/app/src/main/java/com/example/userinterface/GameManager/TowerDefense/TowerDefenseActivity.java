package com.example.userinterface.GameManager.TowerDefense;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.userinterface.GameManager.*;
import com.example.userinterface.R;

/**
 * This is the view object in MVP in a game of tower defense.
 */
public class TowerDefenseActivity extends GameActivity implements TowerDefenseView,
        BadgeCollector {

    Button btnTower1, btnTower2, btnTower3;
    TowerDefensePresenter towerDefensePresenter;
    TowerDefense towerDefense;
    int width;
    int height;
    GameView gameView;
    TowerPositions towerPositions;
    Button[] buttonTowers;
    boolean practiceMode;
    Button selectedTower, sellBtn;
    private boolean sellBtnClicked = false;

    /**
     * This is the OnCreate method for this activity.
     * It is responsible for initializing tower defense presenter and tower defense.
     * It will get a boolean from intent deciding if the game is in practice mode.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tower_defense);
        context = this;
        sellBtn = findViewById(R.id.sell);
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
        towerDefensePresenter = new TowerDefensePresenter(this);
        width = size.x;
        height = size.y;
        TowerPositions.setHeight(height);
        TowerPositions.setWidth(width);
        towerDefense = new TowerDefense(width, height, towerDefensePresenter);
        towerDefensePresenter.setTowerDefense(towerDefense);

    }

    /**
     * This method responds to a back press. It will stop the game and redirect to menu.
     */
    public void onBackPressed() {
        gameView.setGameOver(true);
        gameView.getThread().setRunning(false);
        toMenu();
    }

    /**
     * This method will respond to click on tower.
     * It checks if there is enough money, if there is, it will disable the button for
     * further clicking.
     *
     * @param view the button that is clicked
     */
    public void towerClick(View view) {
        selectedTower = (Button) view;
        if (enoughMoney()) {
            for (Button button : buttonTowers) {
                button.setEnabled(false);
            }
            towerDefensePresenter.towerClick();
            sellBtn.setEnabled(false);
        }

    }

    /**
     * This method will tell tower position to show all its available positions.
     */
    public void showTowerPositions() {
        towerPositions.showAvailable(true);
    }

    /**
     * This method parse the cost from the name of the tower and calls the presenter to calculate
     * if there is enough money to build the tower.
     */
    public boolean enoughMoney() {
        int cost = Integer.parseInt(selectedTower.getContentDescription().toString().
                split(" ")[1]);
        return towerDefensePresenter.enoughMoney(cost);
    }

    /**
     * This method determines which tower had been clicked and calls the presenter to set the tower
     *
     * @param view the button that is clicked
     */
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
            String[] name = selectedTower.getContentDescription().toString().split(" ");
            towerDefensePresenter.setTower((int) view.getX(), (int) view.getY(), name[0], index);
            for (Button button : buttonTowers) {
                button.setEnabled(true);
            }
            towerPositions.showAvailable(false);
            sellBtn.setEnabled(true);
        }else if(sellBtnClicked){
            /*
            Selling the tower selected and return some money
             */
            int index = towerPositions.getTowerNumber((Button) view);
            view.setBackgroundResource(R.drawable.spot);
            int type = towerDefensePresenter.sellTower(index);
            switch (type){
                case 0:
                    towerDefense.addMoney(Integer.parseInt(btnTower1.getContentDescription().
                            toString().split(" ")[1])-10);
                    break;
                case 1:
                    towerDefense.addMoney(Integer.parseInt(btnTower2.getContentDescription().
                            toString().split(" ")[1])-15);
                    break;
                case 2:
                    towerDefense.addMoney(Integer.parseInt(btnTower3.getContentDescription().
                            toString().split(" ")[1])-20);
                    break;
                default:
                    break;
            }
            view.setEnabled(false);
            towerPositions.sellAvailable();
            for (Button item: buttonTowers){
                item.setEnabled(true);
            }
            sellBtnClicked = false;
        }
    }

    /**
     * This method finds all the buttons in the UI after the creation of this activity.
     * It connects game view to tower defense and tells the game view a tower defense game has
     * started.
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
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
        Button[] buttons = {button, button1, button2, button3, button4, button5, button6, button7,
                button8, button9};
        towerPositions = new TowerPositions(buttons);
        towerPositions.setXLocation();
        towerPositions.setYLocation();
        if (gameView != null) {
            gameView.setTowerDefensePresenter(towerDefensePresenter);
            gameView.setGameStart(true);
            gameView.post(() -> setInstructionVisible());
        }

    }

    /**
     * This is an on click method for a start button. It responds to a click and call the on Click
     * method in the presenter.
     *
     * @param v the start button
     */
    public void onPopupDismissal(View v) {
        int gamePlayed = getUser().getStatTracker().getNumOfGames();
        towerDefensePresenter.onPopupDismissal(gamePlayed);
    }

    /**
     * This method will set visible buttons that are going to be used in a game.
     */
    public void setButtonVisible() {
        btnTower1.setVisibility(View.VISIBLE);
        btnTower2.setVisibility(View.VISIBLE);
        btnTower3.setVisibility(View.VISIBLE);
    }

    /**
     * This method specifies what to do once an game ends. It is responsible for communicating to
     * game view and updating the user if the game is not in practice mode.
     * It redirects to intermediate page once the game ends.
     *
     * @param won   if the game is won
     * @param score the score earned
     */
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
        towerDefensePresenter = null;
        goToIntermediate(won, practiceMode);
        this.finish();
        // record score of the level Intermediate page between games
    }

    /**
     * Set the instruction popup window visible. Upon dismissal the game will start.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void  setInstructionVisible(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        @SuppressLint("InflateParams") View popupView = inflater.inflate(R.layout.
                tower_defense_instruction, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        boolean focusable = false;
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(gameView, Gravity.CENTER, 0, 0);
        ((TextView) popupWindow.getContentView().findViewById(R.id.td_instruction)).
                setText(getString(R.string.instruction));
        popupView.setOnTouchListener((v, event) -> {
            popupWindow.dismiss();
            onPopupDismissal(v);
            return true;
        });
    }

    /**
     * sell the tower that is selected
     * @param view
     */
    public void sell(View view){
        if (!towerPositions.isTowerClicked()) {
            towerPositions.sellAvailable();
            for (Button item: buttonTowers){
                item.setEnabled(false);
            }
        }
        sellBtnClicked = true;
    }

    /**
     * @return if user has collected fortunate badge
     */
    @Override
    public boolean collectFortunateBadge() {
        return towerDefensePresenter.getFortunate();
    }

    /**
     * @return if user has collected strategic badge
     */
    @Override
    public boolean collectStrategicBadge() {
        return towerDefensePresenter.getStratgetic();
    }

    /**
     * @return if user has collected adventurous badge
     */
    @Override
    public boolean collectAdventurousBadge() {
        return towerDefensePresenter.getAdventurous();
    }
}




