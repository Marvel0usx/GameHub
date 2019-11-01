package com.example.userinterface.GameManager.HangMan;

public class GameState {

    private int currLen; // number of characters in current word
    private int remainingBalloons; // number of balloons left, decreases each time a wrong letter is guessed
    private int numCorr; // number of letters correctly guessed
    private String currentWord;

    public GameState() {
        currentWord = "BULLETPROOF";
        currLen = currentWord.length();
        remainingBalloons = 6;
    }



}
