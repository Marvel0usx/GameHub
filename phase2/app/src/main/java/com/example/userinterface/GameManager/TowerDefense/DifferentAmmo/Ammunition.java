package com.example.userinterface.GameManager.TowerDefense.DifferentAmmo;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.userinterface.GameManager.TowerDefense.TheEnemy.Enemies;

public abstract class Ammunition implements Ammo {

    private int damage;
    private int x, y;
    private String appearance;
    private int speed;
    private String direction;
    private int textSize = 50;
    private Enemies target;
    private double predictX, predictY;


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

    public void setTarget(Enemies enemy) {
        this.target = enemy;
        if (target.getX() > x) {
            direction = "left";
            x += 130;
            y += 100;
        } else {
            x -= 10;
            y += 100;
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

    public void setDamage(int damage) {
        this.damage = damage;
    }

    void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }


    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        canvas.drawText(appearance, x, y, paint);
        // decide each body parts' coordinates
        System.out.println(textSize);
        System.out.println("hello????");
        System.out.println(appearance + "coooooola");

    }

    public void predictYPosition() {
        if (target.getSpeed() >= this.speed) {
            this.speed += target.getSpeed() - this.speed + 1;
        }
        float A = (float) Math.sqrt(((y - target.getY()) * ((y - target.getY())) +
                ((x - target.getX())) * (x - target.getX())));
        float v1 = target.getSpeed();
        System.out.println(A);
        float alpha = (float) Math.sin(Math.abs(x - target.getX()) / A);
        float v2 = speed;
        System.out.println(Math.sin(alpha));
        float time = (float) (Math.sqrt((A * A) * (v1 * v1 * Math.cos(alpha) * Math.cos(alpha) +
                v2 * v2 - v1 * v1)) - A * v1 * Math.cos(alpha)) / (v2 * v2 - v1 * v1);
        System.out.println(alpha);
        predictY = time * target.getSpeed() + target.getY();
        predictX = target.getX();
    }
}
