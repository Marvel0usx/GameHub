package com.example.userinterface.GameManager;

import java.io.Serializable;
/**
 * A tracker of all the game information.
 */
public class StatTracker implements Serializable {


    private int currScore;
    private int highScore;
    private int numOfGames;
    private int level;

    public StatTracker(int level){
        this.level = level;
    }

    public void addToCurrScore(int gameScore) {
        currScore += gameScore;
    }

    public int getCurrScore() {
        return currScore;
    }

    public void setCurrScore(int currScore) {
        this.currScore = currScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScoreToCurrent() {
        this.highScore = this.currScore;
    }
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setNumOfGames(int numOfGames) {
        this.numOfGames = numOfGames;
    }

    public int getNumOfGames() {
        return numOfGames;
    }

    public void addNumOfGames() {
        this.numOfGames ++;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
