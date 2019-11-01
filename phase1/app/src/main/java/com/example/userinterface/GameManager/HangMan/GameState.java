package com.example.userinterface.GameManager.HangMan;

public class GameState {

    private int currLen; // number of characters in current word
    private int remainingBalloons; // number of balloons left, decreases each time a wrong letter is guessed
    private int numCorr; // number of letters correctly guessed
    private String currentWord;
    private Balloon[] balloons;

    public GameState(String currentWord) {
        this.currentWord = currentWord;
        currLen = currentWord.length();
        remainingBalloons = 6;
        balloons = new Balloon[remainingBalloons];
    }


}
