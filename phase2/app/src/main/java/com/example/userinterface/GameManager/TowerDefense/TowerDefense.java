package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;
import android.util.SparseArray;

import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;
import com.example.userinterface.GameManager.TowerDefense.TheEnemy.Dragon;
import com.example.userinterface.GameManager.TowerDefense.TheEnemy.Enemies;
import com.example.userinterface.GameManager.TowerDefense.TheEnemy.Minion;
import com.example.userinterface.GameManager.TowerDefense.TheEnemy.Orc;
import com.example.userinterface.GameManager.TowerDefense.Towers.RocketTower;
import com.example.userinterface.GameManager.TowerDefense.Towers.Towers;

import java.util.ArrayList;

public class TowerDefense{
/**
 * This is the tower defense class that controls most of the functions of the game.
 */
public class TowerDefense{

    private int currentScore;
    private int lives = 5;
    private int mapHeight;
    private int mapWidth;
    private boolean gameStart = false;
    private boolean adventurous = false;
    private boolean strategetic = false;
    private boolean fortunate = false;


    private boolean win;
    private VariableListenerTowerDefense listener;
    private ArrayList<Ammo> ammo;
    private Towers[] towers = new Towers[10];
    private int cash;
    private InformationBoard informationBoard;
    private int currentWave = 0;
    private SparseArray<ArrayList<Enemies>> waves = new SparseArray<>(3);
    int counter= 0;


    public TowerDefense(int screenWidth, int screenHeight, VariableListenerTowerDefense listener) {
        mapHeight = screenHeight;
        mapWidth = screenWidth;
        ammo = new ArrayList<>();
        cash = 100;
        informationBoard = new InformationBoard(mapHeight, mapWidth);
        informationBoard.setMoneyAppearance(cash);
        waves.append(0, new ArrayList<>());
        waves.append(1, new ArrayList<>());
        waves.append(2, new ArrayList<>());
        this.listener = listener;
    }

    /**
     * updating the information board and all the enemies and bullets.
     * While checking if the game is over or not.
     */
    void update() {
        updateInformationBoard();
        if (gameStart) {
            checkIfOver();
            if (!fortunate || !strategetic)
            checkBadge();
            updateEnemy();
            generateNewWave();
            updateBullet();
        }
    }
    private void checkBadge() {
        if (cash <70) {
            fortunate = true;
        }
        int i = 0;
        for(Towers tower: towers){
            if (tower instanceof RocketTower)
                i ++;
        }
        if (i == 3) {
            strategetic = true;
        }
    }

    /**
     * Generate a new wave of enemy
     */
    private void generateNewWave() {
        if (waves.get(currentWave).isEmpty())
            currentWave++;
    }

    /**
     * Update information shown on the canvas
     */
    private void updateInformationBoard() {
        informationBoard.setMoneyAppearance(cash);
        informationBoard.setLivesAppearance(lives);
    }

    /**
     * Check if the game is over
     * if so then set th variable change to true
     */
    private void checkIfOver() {
        if (lives <= 0 || currentWave == 3) {
            if (lives > 0)
                currentScore += lives * 100; //each life left adds another 100 pts.
            win = lives > 0;
            if (listener != null){
                listener.onGameOver(true);
            }


        }
    }

    /**
     * Update the position and if the towers should generate any bullet.
     */
    private void updateBullet() {
        for (Towers towers : towers) {
            if (towers != null) {
                Enemies temp = getFirstEnemyInRange(
                        towers.getY() - towers.getRange(),
                        towers.getY() + towers.getRange());
                if (temp != null) {
                    Ammo shoot = towers.generateBullet(temp);
                    System.out.println(shoot);
                    if (shoot != null) {
                        ammo.add(shoot);
                        shoot.setLocation(towers.getX(), towers.getY());
                        shoot.setTarget(temp);
                        shoot.predictYPosition();
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

    /**
     * Update the positions and call remove enemy.
     */
    private void updateEnemy() {
        for (Enemies enemy : waves.get(currentWave)) {
            enemy.move();
        }
        removeEnemy();


    }

    /**
     *Removes any enemy that has health lower or equal to 0.
     */
    private void removeEnemy() {
        ArrayList<Enemies> temp = new ArrayList<>();
        for (Enemies e : waves.get(currentWave)) {
            if (e.getHealth() <= 0) {  //if enemy health is 0 remove it
                temp.add(e);
                cash += e.getMoneyGain();
                currentScore += e.getScore();
            }
            if (e.getY() >= mapHeight-300) { //if enemy is out of map remove it
                temp.add(e);
                lives -= e.getDamage();
            }
        }
        for (Enemies item : temp) {
            waves.get(currentWave).remove(item);
        }
    }

    /**
     * Get the first enemy in a particular range.
     * @param lowerbound the lowerbound of the range
     * @param upperbound the upper bound of the range.
     * @return the first enemy in the range provided.
     */
    private Enemies getFirstEnemyInRange(int lowerbound, int upperbound) {
        int temp = lowerbound;
        Enemies first = null;
        for (Enemies item : waves.get(currentWave)) {
            if (item.getY() > lowerbound && item.getY() < upperbound) {
                if (item.getY() > temp) {
                    first = item;
                    temp = item.getY();
                }
            }
        }
        return first;
    }

    /**
     * Add enemy to the game
     * @param hiddenEnemy0 a boolean telling the game whether it should add a hidden enemy.
     * @param hiddenEnemy1 a boolean telling the game whether it should add a hidden enemy.
     * @param hiddenEnemy2 a boolean telling the game whether it should add a hidden enemy.
     */
    void addEnemy(boolean hiddenEnemy0, boolean hiddenEnemy1, boolean hiddenEnemy2) { // GENERICS OR SOME KIND OF PATTERN??
        addMinion(5, 0);
        for (int i = 0; i < 2; i++) {
            addMinion(2, 1);
            addOrc(1, 1);
        }
        for (int i = 0; i < 3; i++) {
            addMinion(3, 2);
            addOrc(2, 2);
        }
        if (hiddenEnemy0)
            addHiddenBoss(0);
        if (hiddenEnemy1)
            addHiddenBoss(1);
        if (hiddenEnemy2) {
            adventurous = true;
            addHiddenBoss(2);
        }

    }

    /**
     * add hidden boss to the game.
     * @param waveNumber on what wave.
     */
    private void addHiddenBoss(int waveNumber) {
        Dragon dragon = new Dragon(waveNumber);
        int x = 50 + mapWidth / 3;
        int y = -(int) (Math.random() * mapHeight / 2) - 100; // a period of time for enemies to walk
        dragon.setLocation(x, y);
        waves.get(waveNumber).add(dragon);
    }

    /**
     * add minions to the game.
     * @param number The amount of minions to be add to the game
     * @param toBeAdded The wave that should be added to.
     */
    private void addMinion(int number, int toBeAdded) {
        for (int i = 0; i < number; i++) {
            Minion minion = new Minion();
            int x = (int) (Math.random() * (mapWidth / 3)) + (mapWidth / 3);
            int y = -(int) (Math.random() * mapHeight / 2) - 100; // a period of time for enemies to walk
            minion.setLocation(x, y);
            waves.get(toBeAdded).add(minion);
        }
    }

    /**
     * Adding orcs to the game.
     * @param number The number of orcs
     * @param toBeAdded The wave that they should be added to.
     */
    private void addOrc(int number, int toBeAdded) {
        for (int i = 0; i < number; i++) {
            Orc orc = new Orc();
            int x = (int) (Math.random() * (mapWidth / 3)) + (mapWidth / 3);
            int y = -(int) (Math.random() * mapHeight / 2) - 100; // a period of time for enemies to walk
            orc.setLocation(x, y);
            waves.get(toBeAdded).add(orc);

        }
    }

    /**
     * draw everything
     * @param canvas The canvas that should be drawn on.
     */
    public void draw(Canvas canvas) {
        for (Enemies item : waves.get(currentWave)) {
            item.draw(canvas);
        }
        for (Ammo ammo : ammo) {
            ammo.draw(canvas);
        }
        informationBoard.draw(canvas);
    }


    /**
     * Add a tower
     * @param index the index it should be added to.
     * @param tower The tower that should be added
     */
    void addTower(int index, Towers tower) {
        towers[index] = tower;
    }

    /**
     * return whether the game is won or not.
     */
    boolean getWin() {
        return win;
    }

    /**
     * @return return thr game score.
     */
     int getGameScore() {
        if (lives > 0)
            currentScore += lives * 100; //each life left adds another 100 pts.
        return currentScore;
    }

    /**
     * @return get the amount of cash of the game
     */
    int getCash() {
        return cash;
    }

    /**
     * cost the user a amount of money.
     * @param cost how much it costs
     */
    void costMoney(int cost) {
        cash -= cost;
    }


    /**
     * set the game to start.
     */
    void setGameStart() {
        this.gameStart = true;
    }



    /**
     * @return a boolean if you have got the badge or not.
     */
    boolean isAdventurous() {
        return adventurous;
    }
    boolean isStrategetic(){
        return strategetic;

    }
    boolean isFortunate(){
        return fortunate;
    }
}
