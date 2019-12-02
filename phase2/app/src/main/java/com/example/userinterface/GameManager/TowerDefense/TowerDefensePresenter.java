package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;

import com.example.userinterface.GameManager.TowerDefense.Towers.TowerFactory;
import com.example.userinterface.GameManager.TowerDefense.Towers.Towers;

/**
 * A Presenter Component of of the towerDefenseGame.
 * It is used to communicate TowerDefense andTowerDefenseActivity.
 */
class TowerDefensePresenter implements VariableListenerTowerDefense {

    private TowerDefenseView towerDefenseView;
    private TowerDefense towerDefense;

    /**
     * TowerDefensePresenter Constructor. It takes in a towerDefenseView, which is the view
     * component in MVP.
     *
     * @param towerDefenseView the related view object of this presenter
     */
    TowerDefensePresenter(TowerDefenseView towerDefenseView) {
        this.towerDefenseView = towerDefenseView;
    }

    /**
     * This is a OnClick method for click on towers. TowerDefenseView will show all
     * the available tower positions.
     */
    void towerClick() {
        towerDefenseView.showTowerPositions();
    }


    /**
     * This is a OnClick method for the start button. This method will decide if a boss for each
     * wave will be added. It will add the enemy into towerdefense and tell it it has started.
     *
     * @param gamePlayed The number of games played by the user in TowerDefenseView
     */
    void onPopupDismissal(int gamePlayed) {
        boolean addBoss0 = false;
        boolean addBoss1 = false;
        boolean addBoss2 = false;
        if (gamePlayed > 5) {
            addBoss0 = Math.random() < 0.3;
            addBoss1 = Math.random() < 0.2;
            addBoss2 = Math.random() < 0.1;
        }
        towerDefense.addEnemy(addBoss0, addBoss1, addBoss2);
        towerDefense.setGameStart();
        towerDefenseView.setButtonVisible();
    }

    /**
     * Setter for towerDefense
     */
    void setTowerDefense(TowerDefense towerDefense) {
        this.towerDefense = towerDefense;
    }

    /**
     * This method will call the draw method for towerDefense.
     */
    void draw(Canvas canvas) {
        towerDefense.draw(canvas);
    }

    /**
     * This method will call the update method for towerDefense.
     */
    void update() {
        towerDefense.update();
    }

    /**
     * This is a listener method that will listen to if a towerDefenseGame is over.
     * If it is over, this method will register if the game has been won and tell the view win or
     * loose and its score.
     */
    @Override
    public void onGameOver(boolean var) {
        boolean won = towerDefense.getWin();
        int score = towerDefense.getGameScore();
        towerDefenseView.endGame(won, score);
        towerDefense = null;
    }

    /**
     * This method will return if there is enough money for towerDefense to build a tower.
     *
     * @param cost the cost of the tower being built
     */
    boolean enoughMoney(int cost) {
        if (cost > towerDefense.getCash()) {
            return false;
        } else {
            towerDefense.costMoney(cost);
            return true;
        }
    }

    /**
     * This method creates a tower and add it into tower defense
     *
     * @param x     the x coordinate of the tower
     * @param y     the y coordinate of the tower
     * @param name  the name of tower
     * @param index number of the slot where this tower is to be placed
     */
    void setTower(int x, int y, String name, int index) {
        TowerFactory towerFactory = new TowerFactory();
        Towers temp = towerFactory.buildTower(name + "tower");
        temp.setLocation(x, y);
        towerDefense.addTower(index, temp);
    }

    int sellTower(int index) {
        return towerDefense.sellTower(index);
    }

    /**
     * Determine if this game of TowerDefense has earned an adventurous badge.
     */
    boolean getAdventurous() {
        return towerDefense.isAdventurous();
    }

    boolean getFortunate() {
        return towerDefense.isFortunate();
    }

    boolean getStratgetic() {
        return towerDefense.isStrategic();
    }
}