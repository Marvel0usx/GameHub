package com.example.userinterface.TowerDefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.ArrayList;

public class TowerDefense {
    //User user;
    int money = 200;
    int waves = 1;
    int lives = 3;
    private int mapHeight;
    private int mapWidth;
    private ArrayList<Enemy> wave1 = new ArrayList<>();

    private int clicker=0;

    public TowerDefense(int screenWidth, int screenHeight) {
        mapHeight = screenHeight;
        mapWidth = screenWidth;
    }

    public void update(){
        ArrayList<Enemy> temp = new ArrayList<>();
        Enemy enemy = getFirstEnemy();
        enemy.hit(clicker);
        clicker=0;
        for (Enemy e: wave1){
            if (e.getHealth() < 0){
                temp.add(e);
            }
            if (e.getY() >= mapHeight-1) {
                temp.add(e);
                lives -= 1;
            }
            e.move();
        }
        for (Enemy item: temp){
            wave1.remove(item);
        }

    }


    public Enemy getFirstEnemy(){
        int yCoor = -800;  //the highest enemy is -800
        Enemy temp = new Minion();
        for (Enemy item: wave1){
            if (item.getY()>yCoor){
                temp = item;
                yCoor = item.getY();
            }
        }
        return temp;
    }

    public void addEnemy(){
        for (int i=0;i<10;i++){
            Minion minion = new Minion();
            int x = (int)(Math.random()*mapWidth);
            int y = -(int)(Math.random()*mapHeight)-100; // a period of time for enemies to walk
            minion.setLocation(x, y);
            wave1.add(minion);

        }

    }

    public void draw(Canvas canvas){
        for (Enemy item: wave1){
            item.draw(canvas);
        }
    }

    public void setClicker(int num){
        clicker=num;
    }
}
