package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;

public class Boss extends Enemy {
    public Boss(int x, int y, int damage, int xSpeed, int ySpeed, int lives, int size){
        super(x,y,damage, xSpeed, ySpeed, lives, size);
        this.appearance = "\uD83D\uDC7E";
        this.paintText.setTextSize(80);
    }

    @Override
    void shoot() {
        //fires bullet objects that deductLife player
        // generate new bullet object and add this object's observer
        // to the bullet object's observer. Return the bullet object.

        this.enemyBullets = BulletFactory.buildBullet("Boss", 1, this.getX(), this.getY());
        setChanged();
        for (Observer obs : getObservers()) {
            for (Bullet bullet : this.enemyBullets)
                bullet.registerObserver(obs);
        }
        for (Bullet bullet : this.enemyBullets)
            bullet.setUpdated(true);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(this.appearance, this.getX(), this.getY(), paintText);
        canvas.drawRect(this.getX()-200, this.getY()-100, this.getX()+ this.getLives(), this.getY() -90, paintText);
    }
}
