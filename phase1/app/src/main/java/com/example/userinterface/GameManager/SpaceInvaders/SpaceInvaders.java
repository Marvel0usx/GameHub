package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvaders implements Observer{
    // Constants
    static int X = 0;
    static int Y = 1;

    // Private attributes
    private int height;
    private int width;
    private Player player;
    private List<SpaceObject> subjects = new ArrayList<>();

    // Initializer
    public SpaceInvaders(int width, int height) {
        this.height = height;
        this.width = width;
    }

    // Implements Observer
    // Register subject to observe
    public void registerSubject(Subject subject){
        // if this is an enemy ship, it's subject will be player and player's bullet;
        // player, enemy and their bullets.
        if (!subjects.contains((SpaceObject) subject))
            subjects.add((SpaceObject) subject);
    }

    public void unregisterSubject(Subject subject) {
        subjects.remove((SpaceObject) subject);
    }

    public void unregisterAll() {
        subjects.clear();
    }

    // update machine-controlled objects
    // this is the responsibility as a SpaceInvader manager
    void run() {
        // create temporary lists to fix concurrent modify error
        ArrayList<SpaceObject> subjectsToRemove = new ArrayList<>();
        ArrayList<SpaceObject> subjectsToMove = new ArrayList<>();
        if (player.getLives() <= 0) {
            // Game lose
        }
        player.updateShootCount();
        player.move(0);
        for (SpaceObject obj : subjects) {
            if (obj.isUpdated())
                continue;
            if (obj instanceof Bullet) {
                if (isAboutBounceBack(obj) || isAtBorder(obj)) {
                    // eliminate bullet & enemy when it flies out
                    ((Subject) obj).unregisterAllObservers();
                    subjectsToRemove.add(obj);
                } else
                    subjectsToMove.add(obj);
            } else if (obj instanceof Enemy) {
                if (isAtBorder(obj)) {
                    ((Subject) obj).unregisterAllObservers();
                    subjectsToRemove.add(obj);
                } else if (isAboutBounceBack(obj))
                    // reverse the heading of enemy
                    obj.setXSpeed(-obj.getXSpeed());
                else
                    subjectsToMove.add(obj);
            }
        }
        for (SpaceObject obj : subjectsToRemove)
            subjects.remove(obj);
        for (SpaceObject obj : subjectsToMove)
            if (obj instanceof Bullet) {
                ((Bullet) obj).move();
            } else if (obj instanceof Enemy) {
                ((Enemy) obj).move();
            }
        for (SpaceObject obj : subjects) {
            obj.setUpdated(false);
        }
    }

    // actual update of the observer
    public void update(SpaceObject obj) {
        int[] newPosition = obj.getUpdate(this);
        ArrayList<SpaceObject> collisions = new ArrayList<>();
        for (SpaceObject o : subjects) {
            if ((o != obj) && !o.getClass().equals(obj.getClass())) {
                if ((o.getX() == newPosition[X]) &&
                        (o.getY() == newPosition[Y])) {
                    collisions.add(o);
                }
            }
        }
        // Only exerts damage to the objects that are in the same position
        // to ensure that the damage is not calculated repeatedly
        for (SpaceObject o : subjects) {

        }
    }

    // Utils
    public void layout() {
        this.player = new Player((this.width >> 1), 1300, 0, 300);
        this.player.registerObserver(this);
        subjects.add(this.player);

        for (int x = 50; x < 500; x += 200)
            subjects.add(new Enemy(x, 100, 100, 2, 1, 100));
        for (int x = 700; x < 1000; x += 200)
            subjects.add(new Enemy(x, 100, 100, 2, 1, 100));
        for (Subject sub : subjects)
            sub.registerObserver(this);
    }

    // change heading when meet x border
    private boolean isAboutBounceBack(@NotNull SpaceObject obj) {
        int nextX;
        if (obj.getXSpeed() < 0)
            // going to the left
            nextX = obj.getX() + obj.getXSpeed();
        else
            // going to right, detecting right border
            nextX = obj.getX() + obj.getXSpeed() + 90;
        return (nextX <= 10) || (nextX >= width);
    }

    // only eliminate object when it is at y border
    private boolean isAtBorder(@NotNull SpaceObject obj) {
        // taking the width into consideration
        int nextY = obj.getY() + obj.getYSpeed();
        return (nextY <= 0) || (nextY >= height);
    }

    public void draw(Canvas canvas) {
        for (SpaceObject item : subjects) item.draw(canvas);
        this.player.draw(canvas);
    }

    void goLeft() {
        // If the player is at the border, then it can't go further
        if (player.getX() - player.getXSpeed() <= 50)
            player.move(0);
        else
            player.move(-1);
    }
    void goRight() {
        if (player.getX() + player.getXSpeed() + 80 >= width)
            player.move(0);
        else
            player.move(1);
    }
}
