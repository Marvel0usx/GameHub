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
        this.numOfGames++;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFortuneNum(){ return this.numFortuneBadges; }

    public int getStrategicNum(){ return this.numStrategicBadges; }

    public int getAdventurousNum(){ return this.numAdventurousBadges; }

    public void addToBadges(boolean fortune, boolean strategy, boolean adventure){
        if (fortune){
            this.numFortuneBadges += 1;
        }
        if (strategy){
            this.numStrategicBadges += 1;
        }
        if (adventure){
            this.numAdventurousBadges += 1;
        }

    }
}
