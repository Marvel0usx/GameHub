package com.example.userinterface.GameManager.SpaceInvaders;

import java.util.ArrayList;
import java.util.List;

abstract class SpaceObject implements Subject {
    private int x;
    private int y;
    private int damage;
    private int xSpeed;
    private int ySpeed;
    private boolean changed;
    private boolean destroyed;
    private boolean updated = false;
    private List<Observer> observers;
    private int size;

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
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

  // Implements Subject
  @Override
  public void registerObserver(Observer observer) {
        if (!observers.contains(observer))
            observers.add(observer);
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
}