package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.List;

public class Enemy extends Ship {
    private boolean changed;
    private EnemyBullet bullet;

    public Enemy(int x, int y, int damage, int ySpeed, int lives){
        super(x, y, damage, ySpeed, lives);
        this.appearance = "💠";
        this.paintText.setColor(Color.GREEN);
        this.paintText.setTextSize(36);
    }

    public Enemy(int x, int y, int damage, int xSpeed, int ySpeed, int lives){
        super(x, y, damage, ySpeed, lives);
        setxSpeed(xSpeed);
        this.appearance = "💠";
        this.paintText.setColor(Color.GREEN);
        this.paintText.setTextSize(36);
    }

    // Implements Subject
    @Override
    public int[] getUpdate(Observer obs) {
        return new int[]{getX(), getY()};
    }

    // Utils
    @Override
    void shoot() {
        //fires bullet objects that damage player
        // generate new bullet object and add this object's observer
        // to the bullet object's observer. Return the bullet object.
        bullet = new EnemyBullet(getX(), getY(), 1, 1);
        for (Observer obs : getObservers())
            bullet.registerObserver(obs);
    }

    void move() {
        setX(getX() + getxSpeed());
        setY(getY() + getySpeed());
    }

    void update(){
        move();
        setChanged();
        bullet = null;
        if (Math.random() < 0.3){
            shoot();
            for (Observer obs : getObservers())
                obs.registerSubject(bullet);
            bullet = null;
        }
        notifyObservers();
    }

    public void draw(Canvas canvas){
        canvas.drawText(this.appearance, this.getX(), this.getY(), paintText);
    }
}
