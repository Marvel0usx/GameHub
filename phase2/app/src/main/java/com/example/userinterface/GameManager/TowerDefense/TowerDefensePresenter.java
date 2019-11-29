package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.util.Log;
import android.widget.Button;

import com.example.userinterface.GameManager.TowerDefense.Towers.TowerFactory;
import com.example.userinterface.GameManager.TowerDefense.Towers.Towers;
import com.example.userinterface.GameManager.VariableChangeListener;

class TowerDefensePresenter implements VariableChangeListener {
    private TowerDefenseView towerDefenseView;
    private TowerDefense towerDefense;

    TowerDefensePresenter(TowerDefenseView towerDefenseView){
        this.towerDefenseView = towerDefenseView;
    }

   void onStartClicked(){
       towerDefense.addEnemy();
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

    public boolean enoughMoney(int cost){
        if (cost > towerDefense.getCash()){
            return false;
        }else{
            towerDefense.costMoney(cost);
            return true;
        }
    }

    public void setTower(int x, int y, String name, int index){
        TowerFactory towerFactory = new TowerFactory();
        Towers temp = towerFactory.buildTower(name+"tower");
        temp.setLocation(x, y);
        towerDefense.addTower(index, temp);
    }
}