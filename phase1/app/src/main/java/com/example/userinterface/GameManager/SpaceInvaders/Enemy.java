package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;

public class Enemy extends Ship {
    private EnemyBullet bullet;

    Enemy(int x, int y, int damage, int xSpeed, int ySpeed, int lives) {
        super(x, y, damage, ySpeed, lives);
        setXSpeed(xSpeed);
        this.appearance = "💠";
        this.paintText.setColor(Color.GREEN);
        this.paintText.setTextSize(80);
    }

    // Implements Subject
    @Override
    public int[] getUpdate(Observer obs) {
        return new int[]{getX(), getY()};
    }

    // Utils
    @Override
    void shoot() {
        //fires bullet objects that deductLife player
        // generate new bullet object and add this object's observer
        // to the bullet object's observer. Return the bullet object.
        this.bullet = new EnemyBullet(getX() + 35, getY(), 100, 4);
        for (Observer obs : getObservers())
            this.bullet.registerObserver(obs);
        this.bullet.setUpdated(true);
    }

    void move() {
        setChanged();
        if (Math.random() < 0.005) {
            shoot();
            for (Observer obs : getObservers())
                obs.registerSubject(this.bullet);
            this.bullet = null;
        }
        setX(getX() + getXSpeed());
        setY(getY() + getYSpeed());
        setUpdated(true);
        notifyObservers();
    }

    public void draw(Canvas canvas){
        canvas.drawText(this.appearance, this.getX(), this.getY(), paintText);
    }
}
