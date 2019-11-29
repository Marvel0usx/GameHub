package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.userinterface.GameManager.TowerDefense.Enemy;

public class Ammunition implements Ammo {

    int damage;
    int x, y;
    String appearance;
    int speed;
    String direction;
    int textSize = 50;
    Enemy target;
    double predictX, predictY;


    @Override
    public void update() {
        double slope = (predictY - y) / (predictX - x);
        double constant = y - slope * x;
        if (direction.equals("right")) {
            y = (int) Math.round((slope * (x -= speed) + constant));
        } else {
            y = (int) Math.round((slope * (x += speed) + constant));
        }
    }

    public double getPrey() {
        return predictY;
    }

    @Override
    public boolean ifHit() {
        if (direction.equals("right")) {
            if (this.x <= predictX) {
                if (target != null) {
                    target.decreaseHealth(damage);
                }
                return true;
            } else {
                return false;
            }
        } else {
            if (this.x >= predictX) {
                if (target != null) {
                    target.decreaseHealth(damage);
                }
                return true;
            } else {
                return false;
            }
        }

    }

    public void setTarget(Enemy enemy) {
        this.target = enemy;
        if (target.getX()>x){
            direction = "left";
            x+=130;
            y+=100;
        }else{
            x-=10;
            y+=100;
            direction = "right";
        }

    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        canvas.drawText(appearance, x, y, paint);
        // decide each body parts' coordinates

    }

    public void predictYPosition() {
        float A = (float) Math.sqrt(((y - target.getY()) * ((y - target.getY())) + ((x - target.getX())) * (x - target.getX())));
        float v1 = target.getSpeed();
        System.out.println(A);
        float alpha = (float) Math.sin(Math.abs(x - target.getX()) / A);
        float v2 = speed;
        System.out.println(Math.sin(alpha));
        float time = (float) (Math.sqrt((A * A) * (v1 * v1 * Math.cos(alpha) * Math.cos(alpha) + v2 * v2 - v1 * v1)) - A * v1 * Math.cos(alpha)) / (v2 * v2 - v1 * v1);
        System.out.println(alpha);
        predictY = time * target.getSpeed() + target.getY();
        predictX = target.getX();
    }
}
