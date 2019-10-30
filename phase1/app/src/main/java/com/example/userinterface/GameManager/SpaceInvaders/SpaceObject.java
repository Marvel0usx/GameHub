package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

abstract class SpaceObject implements Subject {
    // Private attributes
    private int x;
    private int y;
    private int damage;
    private int xSpeed;
    private int ySpeed;
    private boolean changed;
    private boolean isDestroyed;
    private List<Observer> observers;

    // Public attributes
    String appearance;
    Paint paintText = new Paint();

    // Initializer
    SpaceObject(int x, int y) {
        setX(x);
        setY(y);
        isDestroyed = false;
        observers = new ArrayList<>();
    }

    SpaceObject(int x, int y, int damage, int ySpeed){
        setX(x);
        setY(y);
        setDamage(damage);
        setySpeed(ySpeed);
        setxSpeed(0);
        isDestroyed = false;
        observers = new ArrayList<>();
    }

    SpaceObject(int x, int y, int damage, int xSpeed, int ySpeed){
        setX(x);
        setY(y);
        setDamage(damage);
        setxSpeed(xSpeed);
        setySpeed(ySpeed);
        isDestroyed = false;
        observers = new ArrayList<>();
    }

    // Setters and getters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    // Implements Subject
    @Override
    public void registerObserver(Observer observer) {
        // if this is an enemy ship, it's observer will be player;
        // player, enemy.
        if (!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void unregisterAllObservers() {
        observers.clear();
    }

    @Override
    public boolean hasChange() {
        return changed;
    }

    @Override
    public void setChanged() {
        changed = true;
    }

    @Override
    public void clearChanged() {
        changed = false;
    }

    @Override
    public void notifyObservers() {
        if (hasChange()){
            for (Observer obs : getObservers())
                obs.update();
            clearChanged();
        }
    }

    // Utils
    abstract void move();

    public void draw(Canvas canvas){}
}
