package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.SumPathEffect;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvaders implements Observer{
    // Private Constants
    private static int X = 0;
    private static int Y = 1;


    // Public Constant

    // Private attributes
    private int height;
    private int width;
    private int score;
    private int hardness = 1;
    private Player player;
    private List<SpaceObject> subjects = new ArrayList<>();
    public VariableChangeListener var;
    private Scoreboard scoreboard;

    private boolean gameOver = false;
    private boolean isWin = false;

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

        this.player.updateShootCount();
        // this moving command serves the purpose of update
        // player's shoot count and make it fire bullet
        // even if it's not moving
        this.player.move(0);
        this.scoreboard.setAppearance(this.player.getLives(), this.score);



        for (SpaceObject obj : subjects) {
            if (obj.isUpdated())
                continue;
            if (obj.isDestoryed()) {
                subjectsToRemove.add(obj);
                continue;
            }
            // update according to the type of obj
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
        // find all objects that is dead, garbage collecting
        for (SpaceObject obj : subjects) {
            if (obj.isDestoryed())
                subjectsToRemove.add(obj);
        }
        // user local list to modify, avoiding the Concurrent Modification Error
        for (SpaceObject obj : subjectsToRemove)
            // remove from both observer & subject
            subjects.remove(obj); // perhaps we need too consider the reversed reference
        for (SpaceObject obj : subjectsToMove)
            if (obj instanceof Bullet) {
                ((Bullet) obj).move();
            } else if (obj instanceof Enemy) {
                ((Enemy) obj).move();
            }
        for (SpaceObject obj : subjects) {
            obj.setUpdated(false);
        }

        if (this.noEnemies(subjects)){
            this.gameOver = true;
            if (this.player.getLives() > 0) {
                this.isWin = true;
            }
            if (this.var != null)

                this.var.onVariableChange(true);

        }
        if (this.player.getLives() <= 0){
            this.gameOver = true;
            this.isWin = false;
            if (this.var != null)

                this.var.onVariableChange(true);

        }
    }

    private boolean noEnemies(List<SpaceObject> list){
        List list1 = new ArrayList();
        for (SpaceObject e: list){
            if (e instanceof Enemy){
                list1.add(e);
            }
        }
        return list1.isEmpty();
    }

    // Method serves the purpose of collision detection and
    // exerts deductLife to objects
    public void update(SpaceObject obj) {
        // obj is the object (in this cycle) that exerts deductLife to others,
        // i.e. local variable o
        int[] newPosition = obj.getUpdate(this);
        for (SpaceObject o : subjects) {
            if ((o == obj) || o.getClass().equals(obj.getClass()))
                continue;
            if (isCollide(o.getX(), o.getY(), newPosition[X], newPosition[Y])) {
                // Now obj and o are collided and they're not same obj
                exertDamage(obj, o);
            }
        }
    }

    // Utils
    public void layout() {
        this.player = new Player((this.width >> 1), 1300, 0, 300);
        this.player.registerObserver(this);
        subjects.add(this.player);
        this.scoreboard = new Scoreboard();

        for (int x = 50; x < 500; x += 200)
            subjects.add(new Enemy(x, 100, 100, 2 * hardness, hardness, 100));
        for (int x = 700; x < 1000; x += 200)
            subjects.add(new Enemy(x, 100, 100, 2 * hardness, hardness, 100));
        for (Subject sub : subjects)
            sub.registerObserver(this);
        hardness++;
    }

    private boolean isCollide(int x1, int y1, int x2, int y2) {
        // Jan endorsed algorithm
        boolean xCollision = false;
        boolean yCollision = false;

        if (x1 >= x2)
            xCollision = (SpaceObject.UNIWIDTH >= (x1 - x2));
        else // (x1 < x2)
            xCollision = (SpaceObject.UNIWIDTH >= (x2 - x1));

        if (y2 >= y1)
            yCollision = (SpaceObject.UNIHEIGHT >= (y2 - y1));
        else // (y2 < y1)
            yCollision = (SpaceObject.UNIHEIGHT >= (y1 - y2));

        return xCollision & yCollision;
        // End of Jan endorsed algorithm
    }

    private void exertDamage(SpaceObject o1, SpaceObject o2) {
        // we can later add method to receive harm to all Ship
        if (o1 instanceof Player && (o2 instanceof Enemy || o2 instanceof EnemyBullet)) {
            ((Player) o1).setLives(((Player) o1).getLives() - o2.getDamage());
            o2.setDestoryed(true);
        }
        if (o1 instanceof Enemy && (o2 instanceof Player || o2 instanceof PlayerBullet)) {
            if (o2 instanceof Player) {
                ((Player) o2).setLives(((Player) o2).getLives() - o1.getDamage());
                o1.setDestoryed(true);
            } else {
                ((Enemy) o1).setLives(((Enemy) o1).getLives() - o2.getDamage());
                o2.setDestoryed(true);
            }
        }
        if (o1 instanceof EnemyBullet && o2 instanceof Player) {
            ((Player) o2).setLives(((Player) o2).getLives() - o1.getDamage());
            o1.setDestoryed(true);
        }
        if (o1 instanceof PlayerBullet && o2 instanceof Enemy) {
            ((Enemy) o2).setLives(((Enemy) o2).getLives() - o1.getDamage());
            o1.setDestoryed(true);
            this.score += 20;
        }
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
        this.scoreboard.draw(canvas);
    }

    void goLeft() {
        // If the player is at the border, then it can't go further
        if (this.player.getX() <= 10)
            this.player.move(0);
        else
            this.player.move(-1);
    }
    void goRight() {
        if (this.player.getX() + this.player.getXSpeed() + 80 >= width)
            this.player.move(0);
        else
            this.player.move(1);
    }

    void setVariableChangeListener(VariableChangeListener variableChangeListener) {
        this.var = variableChangeListener;
    }

    public boolean isWin(){
        return this.isWin;
    }
    public boolean isGameOver(){
        return this.gameOver;
    }
    public int getScore(){
        return this.score;
    }
}
