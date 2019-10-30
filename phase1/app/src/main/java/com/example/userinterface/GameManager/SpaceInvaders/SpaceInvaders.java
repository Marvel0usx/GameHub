package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvaders implements Observer{
    // Constants
    static int X = 0;
    static int Y = 1;

    // Private attributes
    private int height;
    private int width;
    private int command;
    private int moveDelay;
    private Player player;
    private List<SpaceObject> subjects = new ArrayList<>();

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
        if (!subjects.contains((SpaceObject) subject))
            subjects.add((SpaceObject) subject);
    }

    public void unregisterSubject(Subject subject) {
        subjects.remove((SpaceObject) subject);
    }

    public void unregisterAll() {
        subjects.clear();
    }

    // update machine-controlled objects
    // this is the responsibility as a SpaceInvader manager
    void run() {
        for (SpaceObject obj : subjects) {
            if (obj instanceof Bullet) {
                if (isAboutBounceBack(obj) || isAtBorder(obj)) {
                    // eliminate bullet & enemy when it flies out
                    ((Subject) obj).unregisterAllObservers();
                    subjects.remove(obj);
                } else
                    ((Bullet) obj).move();
            } else if (obj instanceof Enemy) {
                if (isAtBorder(obj)) {
                    ((Subject) obj).unregisterAllObservers();
                    subjects.remove(obj);
                } else if (isAboutBounceBack(obj))
                    // reverse the heading of enemy
                    obj.setXSpeed(-obj.getXSpeed());
                else
                    ((Enemy) obj).move();
            }
        }
    }

    // actual update of the observer
    public void update() {
        int[] newPosition;
        for (Subject sub : subjects) {
            newPosition = sub.getUpdate(this);
        }
    }

    // Utils
    public void layout() {
        player = new Player((this.width >> 1), 1300, 10, 0, 300);
        player.registerObserver(this);
        subjects.add(new Enemy(100, 100, 100, 10, 100));
        subjects.add(new Enemy(300, 200, 100, 10, 100));
        subjects.add(new Enemy(400, 300, 100, 10, 100));
        for (Subject sub : subjects)
            sub.registerObserver(this);
    }

    // change heading when meet x border
    private boolean isAboutBounceBack(@NotNull SpaceObject obj) {
        int nextX = obj.getX() + obj.getXSpeed();
        return (nextX <= 0) || (nextX >= width);
    }

    // only eliminate object when it is at y border
    private boolean isAtBorder(@NotNull SpaceObject obj) {
        int nextY = obj.getY() + obj.getYSpeed();
        return (nextY <= 0) || (nextY >= height);
    }

    public void draw(Canvas canvas) {
        for (SpaceObject item : subjects) item.draw(canvas);
        player.draw(canvas);
    }

    void goLeft() {
//        moveDelay = 1;
//        command = -1;
        // If the player is at the border, then it can't go further
        if (isAtBorder(player))
            player.move(0);
        else
            player.move(-1);
    }
    void goRight() {
//        moveDelay = 1;
//        command = 1;
        if (isAtBorder(player))
            player.move(0);
        else
            player.move(1);
    }
}
