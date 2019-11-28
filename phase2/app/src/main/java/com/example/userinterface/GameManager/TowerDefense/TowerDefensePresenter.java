package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.util.Log;

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

}