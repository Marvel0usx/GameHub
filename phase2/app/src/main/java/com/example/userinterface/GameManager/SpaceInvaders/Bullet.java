package com.example.userinterface.GameManager.SpaceInvaders;

abstract class Bullet extends SpaceObject {
    Bullet(int x, int y, int damage, int ySpeed) {
        super(x, y, damage, ySpeed);
        this.setSize(30);
    }

    // Implements Subject Interface
    // If this is a bullet fired by enemy, the observer will be player
    // Method to update this object
    public int[] getUpdate(Observer obj) {
        return new int[]{getX(), getY()};
    }

    // Utils
    void move() {
        setChanged();
        setUpdated(true);
        setX(getX() + getXSpeed());
        setY(getY() + getYSpeed());
        notifyObservers();
    }
}
