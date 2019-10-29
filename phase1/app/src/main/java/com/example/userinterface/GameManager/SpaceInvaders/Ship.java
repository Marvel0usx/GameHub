package com.example.userinterface.GameManager.SpaceInvaders;

abstract class Ship extends SpaceObject {
    private int lives;
    Ship(float x, float y, int lives) {
        super(x, y);
        this.setLives(lives);
    }

    private void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public Bullet shoot(){
        //fires bullet objects that damage player
        return new Bullet(this.getX(), this.getY(),100);
    }
    public void draw(){

    }
}
