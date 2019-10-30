package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;

import java.util.ArrayList;

public class TowerDefense {
    //User user;
    int money = 200;
    private int currentScore;
    private int lives = 5;
    private int mapHeight;
    private int mapWidth;
    private boolean gameOver = false;
    private boolean win = false;
    private VariableChangeListener listener = null;


    private ArrayList<Enemy> wave1 = new ArrayList<>();

    private int clicker=0;


    public TowerDefense(int screenWidth, int screenHeight) {
        mapHeight = screenHeight;
        mapWidth = screenWidth;
    }

    void update(){
        Enemy enemy = getFirstEnemy();
        if (enemy != null) {
            enemy.hit(clicker);
            clicker = 0;
        }
        removeEnemy(); // remove any enemies with 0 health or out of the map
        if (lives<=0 || wave1.isEmpty()){
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
            if (e.getHealth() <= 0){
                temp.add(e);
                currentScore += e.getScore();
            }
            if (e.getY() >= mapHeight-300) {
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
        for (int i = 0; i < 10; i++) {
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
    boolean getWin(){return win;}
    public int getCurrentScore() {
        return currentScore;
    }




}
