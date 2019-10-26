package com.example.userinterface.SpaceInvaders;

public class SpaceObject {
    private int x;
    private int y;
    private int lives;

    SpaceObject(int x, int y, int lives) {
        this.setLives(lives);
        this.setX(x);
        this.setY(y);
    }

    private void setLives(int lives) {
        this.lives = lives;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLives() {
        return lives;
    }

    public void move(){

    }


}
