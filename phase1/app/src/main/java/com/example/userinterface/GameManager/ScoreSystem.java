package com.example.userinterface.GameManager;

public class ScoreSystem {
    public int gamesPlayed;
    public int highScore;
    public int totalScore;

    public ScoreSystem(){
        gamesPlayed = 0;
        highScore = 0;
        totalScore = 0;
    }
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getTotalScore() {
        return totalScore;
    }




}
