package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;

public class Player extends Ship {
    private PlayerBullet bullet;
    private int shootCount = 10;

    Player(int x, int y, int ySpeed, int lives) {
        super(x, y, 0, 10, ySpeed, lives);
        this.appearance = "🔺";
        this.paintText.setColor(Color.CYAN);
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
        setChanged();
        bullet = new PlayerBullet(getX() + 12, getY(), 100, 8);
        for (Observer obs : getObservers())
            bullet.registerObserver(obs);
    }

    // update the subject and subsequently update the observers
    void move(int directionVector) {
        setChanged();
        setX(getX() + getXSpeed() * directionVector);
        if (shootCount == 0) {
            shoot();
            shootCount = 10;
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
