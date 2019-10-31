package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;

public class Player extends Ship {
    private PlayerBullet bullet;
    private int shootCount = 0;

    Player(int x, int y, int ySpeed, int lives) {
        super(x, y, 0, 40, ySpeed, lives);
        this.appearance = "🔺";
        this.paintText.setColor(Color.CYAN);
        this.paintText.setTextSize(80);
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
        setChanged();
        this.bullet = new PlayerBullet(getX() + 37, getY(), 100, -8);
        for (Observer obs : getObservers())
            this.bullet.registerObserver(obs);
        this.bullet.setUpdated(true);
    }

    // update the subject and subsequently update the observers
    void move(int directionVector) {
        setChanged();
        if (this.shootCount <= 0) {
            shoot();
            this.shootCount = 50;
            for (Observer obs : getObservers())
                obs.registerSubject(this.bullet);
            this.bullet = null;
        }
        setX(getX() + (getXSpeed() * directionVector));
        setUpdated(true);
        notifyObservers();
    }

    public void draw(Canvas canvas){
        canvas.drawText(this.appearance, this.getX(), this.getY(), paintText);
    }
}
