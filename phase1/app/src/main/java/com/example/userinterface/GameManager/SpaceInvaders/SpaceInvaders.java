package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvaders {

    private int height;
    private int width;

    public static List<SpaceObject> spaceObjects = new ArrayList<>();

    public SpaceInvaders(int width, int height){
        this.height = height;
        this.width = width;
    }

    public void draw(Canvas canvas){
        for (SpaceObject item: spaceObjects) item.draw(canvas);
    }
    public void update(){
        for (SpaceObject item: spaceObjects){
            if (item instanceof Enemy){
                item.move();
                ((Enemy) item).shoot();
            }
        }

    }

    public void layout(){
        spaceObjects.add(new Player((int) (this.width/2),1300,300));
        spaceObjects.add(new Enemy(100,100,100));
        spaceObjects.add(new Enemy(300,200,100));
        spaceObjects.add(new Enemy(400,300,100));
    }


}
