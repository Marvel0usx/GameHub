package com.example.userinterface.GameManager.HangMan;

import com.example.userinterface.GameManager.Games;

public class GameState {

    protected int keywordLen; // number of characters in current word
    protected int remainingBalloons; // number of balloons left, decreases each time a wrong letter is guessed
    protected int numCorr; // number of letters correctly guessed
    protected String keyword;
    private Balloon[] balloons;
    private AnswerKeyLetter[] answerKeyLetters;
    private boolean win;
    protected int currentScore;


    public GameState(String keyWord, Balloon[] balloons) {
        this.keyword = keyWord;
        this.keywordLen = keyword.length();
        remainingBalloons = 6;
        this.balloons = balloons;
        this.win = false;
    }

    public String getKeyWord(){
        return this.keyword;
    }

    protected int getCurrentScore() { return this.currentScore; }


    public void loadAnswerKeyLetters(AnswerKeyLetter[] AnswerKeyLetters){
        this.answerKeyLetters = AnswerKeyLetters;
    }

    public void updateState(char charGuessed){ // updates the correct letters guessed (if any)
        boolean correct = false;
        for (int i = 0; i < this.keyword.length(); i++) {
            if (this.answerKeyLetters[i].letter == (charGuessed)) { // a correct letter being guessed
                correct = true;
                this.answerKeyLetters[i].turnBlack(); // shows the letter on screen
                numCorr += 1;
                currentScore += 10;
            }
        }
        if (!correct) {
            remainingBalloons -= 1;
            currentScore -= 20;
            this.balloons[remainingBalloons].disappear();
        }

    }



}