package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvaders {

    private int height;
    private int width;
    private int command;


    public static List<Enemy> enemyList = new ArrayList<>();
    public static Player player;

    public SpaceInvaders(int width, int height) {
        this.height = height;
        this.width = width;

    }

    public void draw(Canvas canvas) {
        for (SpaceObject item : enemyList) item.draw(canvas);
        player.draw(canvas);
    }

    public void update() {
        System.out.println("this is running!");

        for (Enemy item : enemyList) {
            item.move();
            if (item.getX() >= this.width || item.getX() <= 0) {
                item.shift();
                System.out.println("this is running!");
            }
            System.out.println(item.getX());
        }
        player.move(command);
        command = 0;

    }

    public void layout() {
        player = new Player( (this.width >> 1), 1300, 300);
        enemyList.add(new Enemy(100, 100, 100));
        enemyList.add(new Enemy(300, 200, 100));
        enemyList.add(new Enemy(400, 300, 100));
    }

    void goLeft() {
        this.command = -1;
    }

    void goRight() {
        this.command = 1;
    }


}
