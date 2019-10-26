package com.example.userinterface.SpaceInvaders;

public class Enemy extends Ship {
    public Enemy(int x, int y, int lives){
        super(x,y,lives);
    }

    @Override
    public void move(){
        //to be implemented as a movement pattern
    }

    @Override
    public void shoot(){
        //fires bullet objects that damage player
    }
}
