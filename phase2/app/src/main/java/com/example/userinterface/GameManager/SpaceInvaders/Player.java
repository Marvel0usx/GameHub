package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;

public class Player extends Ship {
    private int shootCount = 0;
    private int mode;
    private ArrayList<Bullet> playerBullets;

    Player(int x, int y, int ySpeed, int lives, int size) {
        super(x, y, 0, 40, ySpeed, lives, size);
        this.mode = 1;
    }

    // Setter

    /**
     * subtracts shootcount, for sake of timing shoot()
     */
    void updateShootCount() {
        this.shootCount--;
    }


    /**
     * reflects changes
     * @param obs
     * @return
     */
    @Override
    public int[] getUpdate(Observer obs) {
        return new int[]{getX(), getY()};
    }



    /**
     * shoots a bullet by generating a list of bullets based on current mode, and adds them to
     * an observer list
     */
    @Override
    void shoot() {
        this.playerBullets = BulletFactory.buildBullet("Player", this.mode, this.getX(), this.getY());
        setChanged();
        for (Observer obs : getObservers()) {
            for (Bullet bullet : this.playerBullets)
                bullet.registerObserver(obs);
        }
        for (Bullet bullet : this.playerBullets)
            bullet.setUpdated(true);

    }


    /**
     * controls the movement of the player, as well as the shooting frequency. Also notifies
     * observers about the changes.
     *
     * @param directionVector the direction towards which the player moves
     */
    void move(int directionVector) {
        setChanged();
        if (this.shootCount <= 0) {
            shoot();
            this.shootCount = 20;
            for (Observer obs : getObservers()) {
                for (Bullet bullet: this.playerBullets)
                    obs.registerSubject(bullet);
            }
            this.playerBullets = null;
        }
        setX(getX() + (getXSpeed() * directionVector));
        setUpdated(true);
        notifyObservers();
    }

    /**
     * Sets the current power level of the player
     * @param mode to-be power level of the player
     */
    void setMode(int mode) {
        this.mode = mode;
    }
}
