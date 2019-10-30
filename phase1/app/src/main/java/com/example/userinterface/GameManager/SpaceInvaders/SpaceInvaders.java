package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvaders implements Observer{
    // Constants
    static int X = 0;
    static int Y = 1;

    static List<Enemy> enemyList = new ArrayList<>();
    static Player player;

    // Private attributes
    private int height;
    private int width;
    private int command;
    private int moveDelay;
    private List<Subject> subjects = new ArrayList<>();

    // Initializer
    public SpaceInvaders(int width, int height) {
        this.height = height;
        this.width = width;

    }

    // Implements Observer
    // Register subject to observe
    public void registerSubject(Subject subject){
        // if this is an enemy ship, it's subject will be player and player's bullet;
        // player, enemy and their bullets.
        if (!subjects.contains(subject))
            subjects.add(subject);
    }

    public void unregisterSubject(Subject subject) {
        subjects.remove(subject);
    }

    public void unregisterAll() {
        subjects.clear();
    }

    public void update() {
        if (moveDelay > 0) {
//            player.move(this.command);
            player.move();
            moveDelay = 0;
        }
        for (Enemy item : enemyList) {
            item.move();
            if (item.getX() >= this.width || item.getX() <= 0) {
//                item.shift();
                item.move();
            }
        }
    }

    // Utils
    private boolean isSubjectOutOfBorder(int[] position){
        if (position[X] <= 0 || position[Y] <= 0)
            return true;
        else
            return (position[X] >= width || position[Y] >= height);
    }

    public void draw(Canvas canvas) {
        for (SpaceObject item : enemyList) item.draw(canvas);
        player.draw(canvas);
    }

    public void layout() {
        player = new Player( (this.width >> 1), 1300, 300);
        enemyList.add(new Enemy(100, 100, 100, 15, 100));
        enemyList.add(new Enemy(300, 200, 100, 15, 100));
        enemyList.add(new Enemy(400, 300, 100, 15, 100));
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
