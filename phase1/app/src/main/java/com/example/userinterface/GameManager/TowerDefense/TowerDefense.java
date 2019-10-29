package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;

import java.util.ArrayList;

public class TowerDefense {
    //User user;
    int money = 200;
    int waves = 1;
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
        ArrayList<Enemy> temp = new ArrayList<>();
        for (Enemy e: wave1){
            if (e.getHealth() <= 0){
                temp.add(e);
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
        if (lives<=0 || wave1.isEmpty()){
            gameOver = true;
            win = lives>0;
            if (listener != null)
                listener.onVariableChange(true);
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


}
