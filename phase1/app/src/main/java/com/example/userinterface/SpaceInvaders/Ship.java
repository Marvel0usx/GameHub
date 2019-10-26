package com.example.userinterface.SpaceInvaders;

abstract class Ship extends SpaceObject {
    private int lives;
    Ship(int x, int y, int lives) {
        super(x, y);
        this.setLives(lives);
    }

    private void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void shoot(){
        //fires bullet objects that damage player
    }
}
