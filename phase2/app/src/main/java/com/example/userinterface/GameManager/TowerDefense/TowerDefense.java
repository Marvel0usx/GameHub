package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;

import com.example.userinterface.GameManager.ScoreSystem;
import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;
import com.example.userinterface.GameManager.TowerDefense.Towers.Towers;
import com.example.userinterface.GameManager.VariableChangeListener;

import java.util.ArrayList;

public class TowerDefense implements ScoreSystem {

    private int currentScore;
    private int lives = 5;
    private int mapHeight;
    private int mapWidth;
    private boolean gameOver;

    private boolean win;
    private VariableChangeListener listener = null;
    private ArrayList<Enemy> wave1 = new ArrayList<>();
    private int clicker = 0;
    private ArrayList<Ammo> ammo;
    private Towers[] towers = new Towers[10];
    private int cash;


    public TowerDefense(int screenWidth, int screenHeight) {
        mapHeight = screenHeight;
        mapWidth = screenWidth;
        ammo = new ArrayList<>();
        cash = 100;
    }

    void update() {
        updateEnemy();
        updateBullet();
    }

    private void updateBullet(){
        for (Towers towers: towers) {
            if (towers != null) {
                Enemy temp = getFirstEnemyInRange(
                        towers.getY() - towers.getRange(),
                        towers.getY() + towers.getRange());
                Ammo shoot = towers.generateBullet(temp);
                if (shoot != null) {
                    shoot.setTarget(temp);
                    ammo.add(shoot);
                    shoot.setLocation(towers.getX(), towers.getY());
                }
            }
        }
        ArrayList<Ammo> temp = new ArrayList<>();
        for (Ammo ammo: ammo){
            ammo.update();
            if (ammo.ifHit()){
                getFirstEnemy().health-=ammo.getDamage();
                temp.add(ammo);
            }ammo.setTarget(getFirstEnemy());
        }
        for (Ammo ammo: temp){
            this.ammo.remove(ammo);
        }
    }

    private void updateEnemy(){
        Enemy enemy = getFirstEnemy(); //hit the enemy that is at the frontmost
        if (enemy != null) {
            enemy.hit(clicker);
            clicker = 0;
        }
        removeEnemy();
        if (lives <= 0 || wave1.isEmpty()) { //decide if game is over or not
            gameOver = true;
            if (lives > 0)
                currentScore += lives * 100; //each life left adds another 100 pts.
            win = lives > 0;
            if (listener != null)
                listener.onVariableChange(true);
        }
    }

    private void removeEnemy() {
        ArrayList<Enemy> temp = new ArrayList<>();
        for (Enemy e : wave1) {
            if (e.getHealth() <= 0) {  //if enemy health is 0 remove it
                temp.add(e);
                cash += 20;
                currentScore += e.getScore();
            }
            if (e.getY() >= mapHeight) { //if enemy is out of map remove it
                temp.add(e);
                lives -= 1;
            } else
                e.move();
        }
        for (Enemy item : temp) {
            wave1.remove(item);
        }
    }

    private Enemy getFirstEnemy() {
        int yCoor = -mapHeight / 2 - 100;  //the highest enemy is half the map height above
        Enemy firstEnemy = null;
        for (Enemy item : wave1) {
            if (item.getY() > yCoor) {
                firstEnemy = item;
                yCoor = item.getY();
            }
        }
        return firstEnemy;

    }

    private Enemy getFirstEnemyInRange(int lowerbound, int upperbound){
        int temp = lowerbound;
        Enemy first = null;
        for (Enemy item: wave1){
            if (item.getY()>lowerbound && item.getY() < upperbound){
                if (item.getY() > temp) {
                    first = item;
                    temp = item.getY();
                }
            }
        }
        return first;
    }

    void addEnemy() { // GENERICS OR SOME KIND OF PATTERN??
        addMinion();
        addOrc();
    }

    private void addMinion() {
        for (int i = 0; i < 5; i++) {
            Minion minion = new Minion();
            int x = (int) (Math.random() *  (mapWidth/3)) + (mapWidth/3);
            int y = -(int) (Math.random() * mapHeight / 2) - 100; // a period of time for enemies to walk
            minion.setLocation(x, y);
            wave1.add(minion);
        }
    }
    private void addOrc() {
        for (int i = 0; i < 3; i++) {
            Orc orc  = new Orc();
            int x = (int) (Math.random() *  (mapWidth/3)) + (mapWidth/3);
            int y = -(int) (Math.random() * mapHeight / 2) - 100; // a period of time for enemies to walk
            orc.setLocation(x, y);
            wave1.add(orc);
        }
    }

    public void draw(Canvas canvas) {
        for (Enemy item : wave1) {
            item.draw(canvas);
        }
        for (Ammo ammo: ammo){
            ammo.draw(canvas);
        }

    }

    void setVariableChangeListener(VariableChangeListener variableChangeListener) {
        this.listener = variableChangeListener;
    }

    void addTower(int index, Towers tower){
        towers[index] = tower;
    }

    void clicked() {
        clicker += 1;
    }

    boolean getWin() {
        return win;
    }

    @Override
    public int getGameScore() {
        if (lives > 0)
            currentScore += lives * 100; //each life left adds another 100 pts.
        return currentScore;
    }

    public int getCash() {
        return cash;
    }

    public void costMoney(int cost){
        cash -= cost;
    }

}
