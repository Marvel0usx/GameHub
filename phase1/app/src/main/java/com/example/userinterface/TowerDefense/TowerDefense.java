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
    static ArrayList<Enemy> wave1 = new ArrayList<>();

    static int clicker=0;


    public void update(){
        ArrayList<Enemy> temp = new ArrayList<>();
        for (Enemy item: wave1){
            item.hit();
            clicker = 0;
            if (item.getHealth() < 0){
                temp.add(item);
            }
            item.move();
        }
        for (Enemy item: temp){
            wave1.remove(item);
        }

    }

    public static void addEnemy(){
        wave1.add(new Minion());
    }

    public void draw(Canvas canvas){
        for (Enemy item: wave1){
            item.draw(canvas);
        }
    }

    public static int getClicker() {
        return clicker;
    }
}
