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
    int lives = 5;
    ArrayList<Tower> existingTowers= new ArrayList<>();
    ArrayList<Enemy> wave1 = new ArrayList<>();

    int clicker=0;


    public void update(){
        ArrayList<Enemy> temp = new ArrayList<>();
        Enemy enemy = getFirstEnemy();
        enemy.hit(clicker);
        clicker=0;
        for (Enemy item: wave1){
            if (item.getHealth() < 0){
                temp.add(item);
            }
            item.move();
        }
        for (Enemy item: temp){
            wave1.remove(item);
        }

    }

    public Enemy getFirstEnemy(){
        int yCoor = -800;
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
            int x = (int)(Math.random()*800);
            int y = -(int)(Math.random()*700)-100;
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
