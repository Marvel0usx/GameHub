package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvaders {

    private int height;
    private int width;
    private int command;
    private int moveDelay;


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
        if (moveDelay > 0) {
            player.move(this.command);
            moveDelay = 0;
        }
        for (Enemy item : enemyList) {
            item.move();
            if (item.getX() >= this.width || item.getX() <= 0) {
                item.shift();
            }
        }

    }

    public void layout() {
        player = new Player( (this.width >> 1), 1300, 300);
        enemyList.add(new Enemy(100, 100, 100));
        enemyList.add(new Enemy(300, 200, 100));
        enemyList.add(new Enemy(400, 300, 100));
    }

    void goLeft() {
        this.command = -1;
        this.moveDelay = 1;
    }
    void goRight() {
        this.command = 1;
        this.moveDelay = 1;
    }


}
