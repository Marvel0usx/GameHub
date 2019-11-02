package com.example.userinterface.GameManager;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private int level;

    int getCurrScore() {

        return currScore;
    }

    private int currScore;
    private int highScore;

    int getNumOfGames() {

        return numOfGames;
    }

    private int numOfGames;

    public void addGame() {
        numOfGames += 1;
    }

    String getEmail() {
        return email;
    }

    void setLevel(int level) {
        this.level = level;
    }

    int getLevel() {
        return level;
    }

    int getHighScore() {
        return highScore;
    }

    public void setHighScore(int ScoreOfTheRound) {
        if (ScoreOfTheRound > highScore)
            this.highScore = ScoreOfTheRound;
    }

    public void setNumOfGames(int numOfGames) {
        this.numOfGames = numOfGames;
    }

    public void addToCurrScore(int gameScore) {
        currScore += gameScore;
    }

    public User(String email, int level) {
        this.email = email;
        this.level = level;
    }

}
