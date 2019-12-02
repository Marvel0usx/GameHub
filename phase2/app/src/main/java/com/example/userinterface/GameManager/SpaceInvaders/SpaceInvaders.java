package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;

import com.example.userinterface.GameManager.VariableChangeListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

//import android.graphics.SumPathEffect;

public class SpaceInvaders implements Observer {
    // Private attributes
    private int height;
    private int width;
    private int score;
    private int hardness = 1;
    private Player player;
    private List<SpaceObject> subjects = new ArrayList<>();
    private VariableChangeListener var;
    private Scoreboard scoreboard;
    private int wave;
    private int level;

    private boolean gameOver = false;
    private boolean isWin = false;

    // Initializer
    public SpaceInvaders(int width, int height) {
        this.height = height;
        this.width = width;
        this.wave = 0;

    }

    /**
     * An observer implementation, that registers a subject to a list of observers
     * @param subject subject to register into observer list
     */
    public void registerSubject(Subject subject) {
        // if this is an enemy ship, it's subject will be player and player's bullet;
        // player, enemy and their bullets.
        // noinspection SuspiciousMethodCalls
        if (!subjects.contains(subject))
            subjects.add((SpaceObject) subject);
    }

    /**
     * Removes an object from observer list
     * @param subject object to remove from observer list
     */
    public void unregisterSubject(Subject subject) {
        // Since we know that SpaceObjects are Subject
        // noinspection SuspiciousMethodCalls
        subjects.remove(subject);
    }

    /**
     * Removes all objects from 
     */
    public void unregisterAll() {
        subjects.clear();
    }

    /**
     * Checks responsibilities and dependencies, determines logic within each cycle. Checks if game
     * ends, and advances waves
     */
    void run() {
        // create temporary lists to fix concurrent modify error
        ArrayList<SpaceObject> subjectsToRemove = new ArrayList<>();
        ArrayList<SpaceObject> subjectsToMove = new ArrayList<>();

        this.player.updateShootCount();
        // this moving command serves the purpose of update
        // player's shoot count and make it fire bullet
        // even if it's not moving
        this.player.move(0);
        this.scoreboard.setAppearance(this.player.getLives(), this.score, this.level);

        for (SpaceObject obj : subjects) {
            if (obj.isUpdated())
                continue;
            if (obj.isDestroyed()) {
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
                    //((Subject) obj).unregisterAllObservers();
                    //subjectsToRemove.add(obj);
                    obj.setY(10);
                } else if (isAboutBounceBack(obj))
                    // reverse the heading of enemy
                    obj.setXSpeed(-obj.getXSpeed());
                else
                    subjectsToMove.add(obj);
            }
        }
        // find all objects that is dead, garbage collecting
        for (SpaceObject obj : subjects) {
            if (obj.isDestroyed())
                subjectsToRemove.add(obj);
        }
        // user local list to modify, avoiding the Concurrent Modification Error
        for (SpaceObject obj : subjectsToRemove)
            // remove from both observer & subject
            this.unregisterSubject(obj); // perhaps we need too consider the reversed reference
        for (SpaceObject obj : subjectsToMove)
            if (obj instanceof Bullet) {
                ((Bullet) obj).move();
            } else if (obj instanceof Enemy) {
                ((Enemy) obj).move();
            }
        for (SpaceObject obj : subjects) {
            obj.setUpdated(false);
        }
        if (this.noEnemies(subjects)) {
            this.wave++;
            this.level++;
            spawnWaves();
            for (Subject sub : subjects)
                sub.registerObserver(this);
        }
        if (this.wave == 5) {
            this.gameOver = true;
            unregisterAll();
            if (this.player.getLives() > 0) {
                this.isWin = true;
            }
            if (this.var != null)
                this.var.onVariableChange(true);
        }
        if (this.player.getLives() <= 0) {
            this.gameOver = true;
            this.isWin = false;
            unregisterAll();
            if (this.var != null)
                this.var.onVariableChange(true);
        }
    }


    /**
     * returns true if there are no more enemies in a list
     *
     * @param list a list of spaceObjects
     *
     * @return returns true if there are no instances of Enemy in list
     */
    private boolean noEnemies(List<SpaceObject> list) {
        List<SpaceObject> list1 = new ArrayList<>();
        for (SpaceObject e : list) {
            if (e instanceof Enemy) {
                list1.add(e);
            }
        }
        return list1.isEmpty();
    }

    /**
     * updates game logic by one cycle, checking for collision and movement for all spaceObjects
     */
    public void update(SpaceObject obj) {
        // obj is the object (in this cycle) that exerts deductLife to others,
        // i.e. local variable o
        int X = 0;
        int Y = 1;
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

    /**
     * generates the first layout for the game, instantiating the player, and setting basic variables
     */
    public void layout() {
        this.player = new Player((this.width >> 1), 1500, 0, 500, 40);
        this.player.registerObserver(this);
        subjects.add(this.player);
        scoreboard = new Scoreboard(this.height, this.width);
        this.wave = this.wave + 1;
        this.level = 1;
        spawnWaves();

        for (Subject sub : subjects)
            sub.registerObserver(this);
        hardness++;
    }

    public List<SpaceObject> getUpdate(){
        return subjects;
    }

    /**
     * Changes the wave count, by adding enemies and upgrading the player.
     */
    public void spawnWaves() {
        if (this.wave == 1) {
            this.player.setMode(1);
            for (int x = 50; x < 500; x += 200)
                subjects.add(new Enemy(x, 100, 100, 2 * hardness, hardness, 100, 40));
            for (int x = 700; x < 1000; x += 200)
                subjects.add(new Enemy(x, 100, 100, 2 * hardness, hardness, 100, 40));
        }
        if (this.wave == 2) {
            this.player.setMode(2);
            for (int x = 50; x < 500; x += 200)
                subjects.add(new Enemy(x, 100, 100, 2 * hardness, hardness, 100, 40));
            for (int x = 700; x < 1000; x += 200)
                subjects.add(new Enemy(x, 100, 100, -2 * hardness, hardness, 100, 40));
        }
        if (this.wave == 3) {
            this.player.setMode(3);
            for (int x = 50; x < 500; x += 200)
                subjects.add(new Enemy(x, 100, 100, 0,hardness * 3  ,100, 80));
            for (int x = 700; x < 1000; x += 200)
                subjects.add(new Enemy(x, 100, 100, 0,hardness * 3,100, 80));
        }
        if (this.wave == 4) {
            subjects.add(new Boss(400, 100, 100, 0, hardness, 300, 80));
        }
    }

    /**
     * Returns true if two sets of coordinates collide
     *
     * @param x1 x coordinate of the first object
     * @param y1 y coordinate of the first object
     * @param x2 x coordinate of the second object
     * @param y2 y coordinate of the second object
     *
     * @boolean returns true if x1, y1, are within collision range of x2, y2
     */
    private boolean isCollide(int x1, int y1, int x2, int y2) {
        // Jan endorsed algorithm
        boolean xCollision;
        boolean yCollision;

        if (x1 >= x2)
            xCollision = (80 >= (x1 - x2));
        else // (x1 < x2)
            xCollision = (80 + 40 >= (x2 - x1));

        if (y2 >= y1)
            yCollision = (40 >= (y2 - y1));
        else // (y2 < y1)
            yCollision = (40 >= (y1 - y2));

        return xCollision & yCollision;
        // End of Jan endorsed algorithm
    }

    /**
     * Subtracts lives from spaceObjects, based on the objects that collide
     *
     * @param o1, first object to check
     * @param o2, second object to check
     */
    private void exertDamage(SpaceObject o1, SpaceObject o2) {
        // we can later add method to receive harm to all Ship
        if (o1 instanceof Player && (o2 instanceof Enemy || o2 instanceof EnemyBullet)) {
            ((Player) o1).setLives(((Player) o1).getLives() - o2.getDamage());
            o2.setDestroyed();
        }
        if (o1 instanceof Enemy && (o2 instanceof Player || o2 instanceof PlayerBullet)) {
            if (o2 instanceof Player) {
                ((Player) o2).setLives(((Player) o2).getLives() - o1.getDamage());
                o1.setDestroyed();
            } else {
                ((Enemy) o1).setLives(((Enemy) o1).getLives() - o2.getDamage());
                o2.setDestroyed();
            }
        }
        if (o1 instanceof EnemyBullet && o2 instanceof Player) {
            ((Player) o2).setLives(((Player) o2).getLives() - o1.getDamage());
            o1.setDestroyed();
        }
        if (o1 instanceof PlayerBullet && o2 instanceof Enemy) {
            ((Enemy) o2).setLives(((Enemy) o2).getLives() - o1.getDamage());
            o1.setDestroyed();
            this.score += 20;
        }
    }

    /**
     * returns true if an object is about to hit a left or right border
     *
     * @param obj SpaceObject to check
     *
     * @return returns true if obj is at left or right edge of screen
     */
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

    /**
     * returns true if object is at top or bottom border
     *
     * @param obj SpaceObject to check
     *
     * @return returns true if obj is at top or bottom of screen
     */
    private boolean isAtBorder(@NotNull SpaceObject obj) {
        // taking the width into consideration
        int nextY = obj.getY() + obj.getYSpeed();
        return (nextY <= 0) || (nextY >= height);
    }

//    public void draw(Canvas canvas) {
//        for (SpaceObject item : subjects) item.draw(canvas);
//        this.player.draw(canvas);
//        this.scoreboard.draw(canvas);
//    }

    /**
     * instructs the player to go left
     */
    void goLeft() {
        // If the player is at the border, then it can't go further
        if (this.player.getX() <= 10)
            this.player.move(0);
        else
            this.player.move(-1);
    }

    /**
     * instructs the player to go right
     */
    void goRight() {
        if (this.player.getX() + this.player.getXSpeed() + 80 >= width)
            this.player.move(0);
        else
            this.player.move(1);
    }

    void setVariableChangeListener(VariableChangeListener variableChangeListener) {
        this.var = variableChangeListener;
    }


    /**
     * returns the current condition of the game
     * @return returns true if the game is won
     */
    boolean isWin() {
        return this.isWin;
    }

    /**
     * returns true if the game is over, but not necessarily won
     * @return returns true if the game is over
     */
    boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * returns score
     * @return returns score
     */
    int getScore() {
        return this.score;
    }
}
