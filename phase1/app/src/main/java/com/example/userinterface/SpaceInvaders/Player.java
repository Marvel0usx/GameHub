package com.example.userinterface.SpaceInvaders;

public class Player extends Ship {

    public Player(int x, int y, int lives){
        super(x,y,lives);
    }

    @Override
    public void move(){
        //to be implemented as a touch event
    }

    @Override
    public void shoot(){
        //fires bullet objects that damage player
    }
}
