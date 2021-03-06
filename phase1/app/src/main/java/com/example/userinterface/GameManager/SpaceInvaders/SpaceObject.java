package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

abstract class SpaceObject implements Subject {
    static int WIDTH = 40;
    static int HEIGHT = 40;

    // Private attributes
    private int x;
    private int y;
    private int damage;
    private int xSpeed;
    private int ySpeed;
    private boolean changed;
    private boolean destroyed;
    private boolean updated = false;
    private List<Observer> observers;

    // Public attributes
    String appearance;
    Paint paintText = new Paint();

    // Initializer
    SpaceObject(int x, int y, int damage, int ySpeed) {
        setX(x);
        setY(y);
        setDamage(damage);
        setYSpeed(ySpeed);
        setXSpeed(0);
        observers = new ArrayList<>();
    }

    SpaceObject(int x, int y, int damage, int xSpeed, int ySpeed) {
        setX(x);
        setY(y);
        setDamage(damage);
        setXSpeed(xSpeed);
        setYSpeed(ySpeed);
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

    int getDamage() {
        return damage;
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }

    int getXSpeed() {
        return xSpeed;
    }

    void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    int getYSpeed() {
        return ySpeed;
    }

    private void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    List<Observer> getObservers() {
        return observers;
    }

    boolean isUpdated() {
        return updated;
    }

    void setUpdated(boolean updated) {
        this.updated = updated;
    }

    boolean isDestroyed() {
        return destroyed;
    }

    void setDestroyed() {
        this.destroyed = true;
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
        if (hasChange()) {
            for (Observer obs : getObservers())
                obs.update(this);
            clearChanged();
        }
    }

    // Utils
    public void draw(Canvas canvas) {
    }
}