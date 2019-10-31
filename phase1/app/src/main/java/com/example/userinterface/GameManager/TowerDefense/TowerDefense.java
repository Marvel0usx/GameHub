package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;

import com.example.userinterface.GameManager.ScoreSystem;

import java.util.ArrayList;

public class TowerDefense implements ScoreSystem {
    //User user;
    int money = 200;
    private int currentScore;
    private int lives = 5;
    private int mapHeight;
    private int mapWidth;
    private boolean gameOver;
    private boolean win;
    private VariableChangeListener listener = null;


    private ArrayList<Enemy> wave1 = new ArrayList<>();

    private int clicker=0;


    public TowerDefense(int screenWidth, int screenHeight) {
        mapHeight = screenHeight;
        mapWidth = screenWidth;
    }

    void update(){
        Enemy enemy = getFirstEnemy(); //hit the enemy that is at the frontmost
        if (enemy != null) {
            enemy.hit(clicker);
            clicker = 0;
        }
        removeEnemy();
        if (lives<=0 || wave1.isEmpty()){ //decide if game is over or not
            gameOver = true;
            if (lives > 0)
                currentScore += lives *100; //each life left adds another 100 pts.
            win = lives>0;
            if (listener != null)
                listener.onVariableChange(true);
        }
    }
    private void removeEnemy(){
        ArrayList<Enemy> temp = new ArrayList<>();
        for (Enemy e: wave1){
            if (e.getHealth() <= 0){  //if enemy health is 0 remove it
                temp.add(e);
                currentScore += e.getScore();
            }
            if (e.getY() >= mapHeight-300) { //if enemy is out of map remove it
                temp.add(e);
                lives -= 1;
            }
            else
                e.move();
        }
        for (Enemy item: temp){
            wave1.remove(item);
        }
    }

    private Enemy getFirstEnemy() {
        int yCoor = -mapHeight / 2 - 100;  //the highest enemy is half the map height above
        Enemy firstEnemy = null;
        for (Enemy item : wave1) {
            if (item.getY() > yCoor) {
                firstEnemy = item;
                yCoor = item.getY();
                }
            }
        return firstEnemy;

    }

    void addEnemy(){
        addMinion();
        }

    private void addMinion() {
        for (int i = 0; i < 10; i++) { //add ten minions
            Minion minion = new Minion();
            int x = (int) (Math.random() * mapWidth);
            int y = -(int) (Math.random()* mapHeight/2)-100; // a period of time for enemies to walk
            minion.setLocation(x, y);
            wave1.add(minion);
        }
    }


    public void draw(Canvas canvas){
        for (Enemy item: wave1){
            item.draw(canvas);
        }

    }

    void setVariableChangeListener(VariableChangeListener variableChangeListener) {
        this.listener = variableChangeListener;
    }

    void clicked(){clicker += 1;}

    boolean getWin(){
        return win;
    }

    @Override
    public int getGameScore() {
        if (lives > 0)
            currentScore += lives * 100; //each life left adds another 100 pts.
        return currentScore;
    }
}
