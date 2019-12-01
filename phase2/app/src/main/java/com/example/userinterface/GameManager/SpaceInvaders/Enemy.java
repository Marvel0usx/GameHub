package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;

public class Enemy extends Ship {
    private int mode;
    public ArrayList<Bullet> enemyBullets;

    Enemy(int x, int y, int damage, int xSpeed, int ySpeed, int lives) {
        super(x, y, damage, ySpeed, lives);
        setXSpeed(xSpeed);
        this.appearance = "\uD83D\uDC19";
        this.paintText.setColor(Color.GREEN);
        this.paintText.setTextSize(80);
        this.mode = 1;
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

        this.enemyBullets = BulletFactory.buildBullet("Enemy", this.mode, this.getX(), this.getY());
        setChanged();
        for (Observer obs : getObservers()) {
            for (Bullet bullet : this.enemyBullets)
                bullet.registerObserver(obs);
        }
        for (Bullet bullet : this.enemyBullets)
            bullet.setUpdated(true);
    }

    void move() {
        setChanged();
        if (Math.random() < 0.005) {
            shoot();
            for (Observer obs : getObservers()) {
                for (Bullet bullet: this.enemyBullets)
                    obs.registerSubject(bullet);
            }
            this.enemyBullets = null;
        }
        setX(getX() + getXSpeed());
        setY(getY() + getYSpeed());
        setUpdated(true);
        notifyObservers();
    }

    void setMode(int mode){
        this.mode = mode;
    }

    public void draw(Canvas canvas) {
        canvas.drawText(this.appearance, this.getX(), this.getY(), paintText);
        canvas.drawRect(this.getX(), this.getY()-100, this.getX()+ this.getLives(), this.getY() -90, paintText);
    }
}
