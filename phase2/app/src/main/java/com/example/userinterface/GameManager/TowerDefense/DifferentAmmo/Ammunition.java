package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.userinterface.GameManager.TowerDefense.Enemy;

public class Ammunition implements Ammo {

    int damage;
    int x,y;
    String appearence;
    int speed;
    String direction;

    @Override
    public void update(Enemy enemy) {
        int enemyXPosition = enemy.getX();
        int enemyYPosition = enemy.getY();
        float slope = (enemyYPosition-y)/(enemyXPosition-x);
        float constant = y-slope*x;
        if (direction.equals("right")){
            y = Math.round((slope*(x-=2)+constant));
        }else{
            y = Math.round((slope*(x+=2)+constant));
        }
    }

    @Override
    public boolean ifHit(int x) {
        if (direction.equals("right")){
            if (this.x <= x){
                return true;
            }else{
                return false;
            }
        }else{
            if (this.x >= x){
                return true;
            }else{
                return false;
            }
        }

    }

    @Override
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(50);
        canvas.drawText(appearence, x, y, paint);
        // decide each body parts' coordinates

    }
}
