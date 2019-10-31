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

    // Setters and getters
    public void setLives(int lives) {
        this.lives = lives;
    }

    int getLives() {
        return lives;
    }


    // Utils
    abstract void shoot();
}
