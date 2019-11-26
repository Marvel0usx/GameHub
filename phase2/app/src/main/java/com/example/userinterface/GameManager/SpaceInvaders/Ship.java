package com.example.userinterface.GameManager.SpaceInvaders;

abstract class Ship extends SpaceObject {
    private int lives;

    // Initializer
    Ship(int x, int y, int damage, int ySpeed, int lives) {
        super(x, y, damage, ySpeed);
        this.setLives(lives);
    }

    Ship(int x, int y, int damage, int xSpeed, int ySpeed, int lives) {
        super(x, y, damage, xSpeed, ySpeed);
        this.setLives(lives);
    }

    int getLives() {
        return lives;
    }

    // Setters and getters
    void setLives(int lives) {
        this.lives = lives;
        if (this.lives <= 0)
            setDestroyed();
    }

    // Utils
    abstract void shoot();
}
