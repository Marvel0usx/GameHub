package com.example.userinterface.GameManager;


/**
 * A tracker of all the game information.
 */
public class StatTracker {


    private int currScore;
    private int highScore;
    private int numOfGames;
    private int level;
    private int numFortuneBadges;
    private int numAdventurousBadges;
    private int numStrategicBadges;


    public StatTracker(int level) {
        this.level = level;
    }

    public void addToCurrScore(int gameScore) {
        currScore += gameScore;
    }

    int getCurrScore() {
        return currScore;
    }

    public void setCurrScore(int currScore) {
        this.currScore = currScore;
    }

    int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    void setHighScoreToCurrent() {
        this.highScore = this.currScore;
    }

    public int getNumOfGames() {
        return numOfGames;
    }

    public void setNumOfGames(int numOfGames) {
        this.numOfGames = numOfGames;
    }

    void addNumOfGames() {
        this.numOfGames++;
    }

    int getLevel() {
        return level;
    }

    void setLevel(int level) {
        this.level = level;
    }

    int getFortuneNum() {
        return this.numFortuneBadges;
    }

    int getStrategicNum() {
        return this.numStrategicBadges;
    }

    int getAdventurousNum() {
        return this.numAdventurousBadges;
    }

    public void addToBadges(boolean fortune, boolean strategy, boolean adventure) {
        if (fortune) {
            this.numFortuneBadges += 1;
        }
        if (strategy) {
            this.numStrategicBadges += 1;
        }
        if (adventure) {
            this.numAdventurousBadges += 1;
        }

    }
}
