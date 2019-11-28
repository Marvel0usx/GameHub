package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.util.Log;
import android.util.SparseArray;

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
    private boolean gameStart = false;

    private boolean win;
    private VariableChangeListener listener;
    private ArrayList<Ammo> ammo;
    private Towers[] towers = new Towers[10];
    private int cash;
    private InfromationBoard infromationBoard;
    private int currentWave = 0;
    private SparseArray<ArrayList<Enemy>> waves = new SparseArray<>(3);


    public TowerDefense(int screenWidth, int screenHeight, VariableChangeListener listener) {
        mapHeight = screenHeight;
        mapWidth = screenWidth;
        ammo = new ArrayList<>();
        cash = 100;
        infromationBoard = new InfromationBoard(mapHeight, mapWidth);
        infromationBoard.setAppearance(cash);
        waves.append(0, new ArrayList<>());
        waves.append(1, new ArrayList<>());
        waves.append(2, new ArrayList<>());
        this.listener = listener;

    }

    void update() {
        updateInformationBoard();
        if (gameStart) {
            checkIfOver();
            updateEnemy();
            Log.d("message", "current wave = " + currentWave);
            generateNewWave();
            updateBullet();
        }
    }
    private void generateNewWave(){
        if (waves.get(currentWave).isEmpty())
            currentWave ++;
    }
    private void updateInformationBoard() {
        infromationBoard.setAppearance(cash);
    }
    private void checkIfOver(){
        if (lives <= 0 || currentWave == 3) {
            //decide if game is over or not
            if (lives > 0)
                currentScore += lives * 100; //each life left adds another 100 pts.
            win = lives > 0;
            if (listener != null)
                listener.onVariableChange(true);
        }
    }
    private void updateBullet() {
        for (Towers towers : towers) {
            if (towers != null) {
                Enemy temp = getFirstEnemyInRange(
                        towers.getY() - towers.getRange(),
                        towers.getY() + towers.getRange());
                if (temp != null) {
                    Ammo shoot = towers.generateBullet(temp);
                    if (shoot != null) {
                        ammo.add(shoot);
                        System.out.println("hella");
                        shoot.setTarget(temp);
                        shoot.setLocation(towers.getX(), towers.getY());
                        shoot.predictYPosition();
                        System.out.println(shoot.getPrey() + "hello");
                    }
                }
            }
        }
        ArrayList<Ammo> temp = new ArrayList<>();
        for (Ammo ammo : ammo) {
            ammo.update();
            if (ammo.ifHit()) {
                temp.add(ammo);
            }
        }
        for (Ammo item : temp) {
            ammo.remove(item);
        }
    }

    private void updateEnemy() {
        for (Enemy enemy : waves.get(currentWave)) {
            enemy.move();
        }
        removeEnemy();


    }

    private void removeEnemy() {
        ArrayList<Enemy> temp = new ArrayList<>();
        for (Enemy e : waves.get(currentWave)) {
            if (e.getHealth() <= 0) {  //if enemy health is 0 remove it
                temp.add(e);
                cash += e.getMoneyGain();
                currentScore += e.getScore();
            }
            if (e.getY() >= mapHeight) { //if enemy is out of map remove it
                temp.add(e);
                lives -= 1;
            }
        }
        for (Enemy item : temp) {
            waves.get(currentWave).remove(item);
            item = null;
            System.out.println(item);
        }
    }

    private Enemy getFirstEnemyInRange(int lowerbound, int upperbound) {
        int temp = lowerbound;
        Enemy first = null;
        for (Enemy item : waves.get(currentWave)) {
            if (item.getY() > lowerbound && item.getY() < upperbound) {
                if (item.getY() > temp) {
                    first = item;
                    temp = item.getY();
                }
            }
        }
        return first;
    }

    void addEnemy() { // GENERICS OR SOME KIND OF PATTERN??
        addMinion(5,0);

        for (int i = 0; i < 2; i++){
            addMinion(2, 1);
            addOrc(1, 1);
        }
        for (int i = 0; i < 3; i++){
            addMinion(3, 2);
            addOrc(2, 2);
        }
    }

    private void addMinion(int number, int toBeAdded) {
        for (int i = 0; i < number; i++) {
            Minion minion = new Minion();
            int x = (int) (Math.random() * (mapWidth / 3)) + (mapWidth / 3);
            int y = -(int) (Math.random() * mapHeight / 2) - 100; // a period of time for enemies to walk
            minion.setLocation(x, y);
            waves.get(toBeAdded).add(minion);
        }
    }

    private void addOrc(int number, int toBeAdded) {
        for (int i = 0; i < number; i++) {
            Orc orc = new Orc();
            int x = (int) (Math.random() * (mapWidth / 3)) + (mapWidth / 3);
            int y = -(int) (Math.random() * mapHeight / 2) - 100; // a period of time for enemies to walk
            orc.setLocation(x, y);
            waves.get(toBeAdded).add(orc);

        }
    }

    public void draw(Canvas canvas) {
        for (Enemy item : waves.get(currentWave)) {
            item.draw(canvas);
        }
        for (Ammo ammo : ammo) {
            ammo.draw(canvas);
        }
        infromationBoard.draw(canvas);
    }

    void setVariableChangeListener(VariableChangeListener variableChangeListener) {
        this.listener = variableChangeListener;
    }

    void addTower(int index, Towers tower) {
        towers[index] = tower;
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

    int getCash() {
        return cash;
    }

    void costMoney(int cost) {
        cash -= cost;
    }


    void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }


}
