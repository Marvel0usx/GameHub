package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.userinterface.GameManager.TowerDefense.Enemy;

public class Ammunition implements Ammo {

    int damage;
    int x,y;
    String appearence;
    int speed;
    String direction;
    int textSize = 50;
    Enemy target;

    @Override
    public void update() {
        int enemyXPosition = target.getX();
        int enemyYPosition = target.getY();
        float slope = (enemyYPosition-y)/(enemyXPosition-x);
        float constant = y-slope*x;
        if (direction.equals("right")){
            y = Math.round((slope*(x-=speed)+constant));
        }else{
            y = Math.round((slope*(x+=speed)+constant));
        }
    }

    @Override
    public boolean ifHit() {
        if (direction.equals("right")){
            if (this.x <= target.getX()){
                return true;
            }else{
                return false;
            }
        }else{
            if (this.x >= target.getX()){
                return true;
            }else{
                return false;
            }
        }

    }

    public void setTarget(Enemy enemy){
        this.target = enemy;
    }

    @Override
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int getDamage(){
        return damage;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        canvas.drawText(appearence, x, y, paint);
        // decide each body parts' coordinates

    }
}
