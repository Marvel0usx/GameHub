package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;

public class Player extends Ship {
    private PlayerBullet bullet;

    Player(int x, int y, int lives){
        super(x, y, 0, 0, lives);
        this.appearance = "🗼";
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
        bullet = new PlayerBullet(getX(), getY(), 100, 10);
        for (Observer obs : getObservers())
            bullet.registerObserver(obs);
    }

    void move() {
        setX(getX() + getxSpeed());
        setY(getY() + getySpeed());
    }

    void update(){
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
