package com.example.userinterface.GameManager.SpaceInvaders;

import java.util.ArrayList;

public class Enemy extends Ship {
    private int mode;
    ArrayList<Bullet> enemyBullets;

    Enemy(int x, int y, int damage, int xSpeed, int ySpeed, int lives, int size) {
        super(x, y, damage, ySpeed, lives, size);
        setXSpeed(xSpeed);
        this.mode = 1;
    }

    // Implements Subject
    @Override
    public int[] getUpdate(Observer obs) {
        return new int[]{getX(), getY()};
    }

    /**
     * directs the bulletFactory to produce enemyBullets, and registers them to the observer class
     * to allow for collision detection
     */
    @Override
    void shoot() {
        this.enemyBullets = BulletFactory.buildBullet("Enemy", this.mode, this.getX(), this.getY());
        setChanged();
        for (Observer obs : getObservers()) {
            for (Bullet bullet : this.enemyBullets)
                bullet.registerObserver(obs);
        }
        for (Bullet bullet : this.enemyBullets)
            bullet.setUpdated(true);
    }

    /**
     * handles the movement as well as shoot() timings of this ship, as well as notify observers of
     * movement
     */
    void move() {
        setChanged();
        if (Math.random() < 0.005) {
            shoot();
            for (Observer obs : getObservers()) {
                for (Bullet bullet: this.enemyBullets) {
                    obs.registerSubject(bullet);
                }
            }
            this.enemyBullets = null;
        }
        setX(getX() + getXSpeed());
        setY(getY() + getYSpeed());
        setUpdated(true);
        notifyObservers();
    }
}
