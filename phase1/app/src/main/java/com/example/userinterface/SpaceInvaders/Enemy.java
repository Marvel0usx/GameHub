package com.example.userinterface.SpaceInvaders;

public class Enemy extends SpaceObject {
    public Enemy(int x, int y, int lives){
        super(x,y,lives);
    }
    public void move(){
        //to be implemented as a movement pattern
    }
    public void shoot(){
        //fires bullet objects that damage player
    }
}
