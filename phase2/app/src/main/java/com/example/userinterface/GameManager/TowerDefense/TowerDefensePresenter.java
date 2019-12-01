package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import com.example.userinterface.GameManager.TowerDefense.Towers.TowerFactory;
import com.example.userinterface.GameManager.TowerDefense.Towers.Towers;
import com.example.userinterface.GameManager.VariableChangeListener;

class TowerDefensePresenter implements VariableChangeListener {
    private TowerDefenseView towerDefenseView;
    private TowerDefense towerDefense;

    TowerDefensePresenter(TowerDefenseView towerDefenseView){
        this.towerDefenseView = towerDefenseView;
    }

    void towerClick() {
        towerDefenseView.showTowerPositions();
    }

    void onStartClicked(int gamePlayed){
        boolean addBoss0 = false;
        boolean addBoss1 = false;
        boolean addBoss2 = false;
        if (gamePlayed > 5){
            addBoss0 = Math.random() < 1;
            addBoss1 = Math.random() < 1;
            addBoss2 = Math.random() < 1;
        }
       towerDefense.addEnemy(addBoss0, addBoss1, addBoss2);
       towerDefense.setGameStart(true);
       towerDefenseView.setButtonVisible();
   }

   void setTowerDefense(TowerDefense towerDefense){
        this.towerDefense = towerDefense;
   }
   void draw(Canvas canvas){
       towerDefense.draw(canvas);
   }
   void update(){
       towerDefense.update();
   }

    @Override
    public void onVariableChange(boolean var) {
        boolean won = towerDefense.getWin();
        int score = towerDefense.getGameScore();
        towerDefense = null;
        towerDefenseView.endGame(won, score);
    }

    boolean enoughMoney(int cost){
        if (cost > towerDefense.getCash()){
            return false;
        }else{
            towerDefense.costMoney(cost);
            return true;
        }
    }

    void setTower(int x, int y, String name, int index){
        TowerFactory towerFactory = new TowerFactory();
        Towers temp = towerFactory.buildTower(name+"tower");
        temp.setLocation(x, y);
        towerDefense.addTower(index, temp);
    }

    boolean getAdventurous(){
        return towerDefense.getAdventurous();
    }
}