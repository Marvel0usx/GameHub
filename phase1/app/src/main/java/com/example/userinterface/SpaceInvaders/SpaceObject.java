package com.example.userinterface.SpaceInvaders;

abstract class SpaceObject {
    private int x;
    private int y;

    SpaceObject(int x, int y) {
        this.setX(x);
        this.setY(y);
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

    public void move(){

    }


}
