package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;

public class Player extends Ship {
    private int shootCount = 0;
    private int mode;
    private ArrayList<Bullet> playerBullets;

    Player(int x, int y, int ySpeed, int lives) {
        super(x, y, 0, 40, ySpeed, lives);
        this.appearance = "🔺";
        this.paintText.setColor(Color.CYAN);
        this.paintText.setTextSize(80);
        this.mode = 1;
    }

    // Setter
    void updateShootCount() {
        this.shootCount--;
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

        this.playerBullets = BulletFactory.buildBullet("Player", this.mode, this.getX(), this.getY());
        setChanged();
        for (Observer obs : getObservers()) {
            for (Bullet bullet : this.playerBullets)
                bullet.registerObserver(obs);
        }
        for (Bullet bullet : this.playerBullets)
            bullet.setUpdated(true);

    }

    // update the subject and subsequently update the observers
    void move(int directionVector) {
        setChanged();
        if (this.shootCount <= 0) {
            shoot();
            this.shootCount = 50;
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

    void setMode(int mode) {
        this.mode = mode;
    }


    public void draw(Canvas canvas) {
        canvas.drawText(this.appearance, this.getX(), this.getY(), paintText);
    }
}
