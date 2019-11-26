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
    private boolean gameStart = false;

    private boolean win;
    private VariableChangeListener listener = null;
    private ArrayList<Enemy> wave1 = new ArrayList<>();
    private ArrayList<Ammo> ammo;
    private Towers[] towers = new Towers[10];
    private int cash;
    private InfromationBoard infromationBoard;


    public TowerDefense(int screenWidth, int screenHeight) {
        mapHeight = screenHeight;
        mapWidth = screenWidth;
        ammo = new ArrayList<>();
        cash = 100;
        infromationBoard = new InfromationBoard(mapHeight, mapWidth);
        infromationBoard.setAppearance(cash);
    }

    void update() {
        updateInformationBoard();
        if (gameStart) {
            updateEnemy();
            updateBullet();
        }
    }

    private void updateInformationBoard(){
        infromationBoard.setAppearance(cash);
    }

    private void updateBullet(){
        for (Towers towers: towers) {
            if (towers != null) {
                Enemy temp =getFirstEnemyInRange(
                        towers.getY()-towers.getRange(),
                        towers.getY()+towers.getRange());
                if (temp!=null) {
                    Ammo shoot = towers.generateBullet(temp);
                    if (shoot != null) {
                        ammo.add(shoot);
                        System.out.println("hella");
                        shoot.setTarget(temp);
                        shoot.setLocation(towers.getX(), towers.getY());
                        shoot.predictYPosition();
                        System.out.println(shoot.getPrey()+"hello");
                    }
                }
            }
        }
        ArrayList<Ammo> temp = new ArrayList<>();
        for (Ammo ammo: ammo) {
            ammo.update();
            if (ammo.ifHit()){
                temp.add(ammo);
            }
        }
        for (Ammo item: temp){
            ammo.remove(item);
        }
    }

    private void updateEnemy(){
        for (Enemy enemy: wave1){
            enemy.move();
        }
        removeEnemy();
        if (lives <= 0 || wave1.isEmpty()) { //decide if game is over or not
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
                cash += e.getMoneyGain();
                currentScore += e.getScore();
            }
            if (e.getY() >= mapHeight) { //if enemy is out of map remove it
                temp.add(e);
                lives -= 1;
            }
        }
        for (Enemy item : temp) {
            wave1.remove(item);
        }
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
        infromationBoard.draw(canvas);
    }

    void setVariableChangeListener(VariableChangeListener variableChangeListener) {
        this.listener = variableChangeListener;
    }

    void addTower(int index, Towers tower){
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

    public int getCash() {
        return cash;
    }

    public void costMoney(int cost){
        cash -= cost;
    }


    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }


}
