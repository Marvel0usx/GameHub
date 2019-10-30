package com.example.userinterface.GameManager;

import java.io.Serializable;

class ScoreSystem implements Serializable {
    int gamesPlayed;
    int highScore;
    int currentScore;


    public ScoreSystem(int gamesPlayed, int highScore, int currentScore){
        this.gamesPlayed = gamesPlayed;
        this.highScore = highScore;
        this.currentScore = currentScore;
    }
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }


    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

}
