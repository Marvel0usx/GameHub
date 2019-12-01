package com.example.userinterface.GameManager.HangMan;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.userinterface.GameManager.BadgeCollector;
import com.example.userinterface.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class GameState implements BadgeCollector {

    int keywordLen; // number of characters in current word
    int remainingBalloons; // number of balloons left, decreases each time a wrong letter is gues【sed
    int numCorr; // number of letters correctly guessed
    Difficulty difficulty;
    private String keyword;
    private Balloon[] balloons; // an array of Balloon objects
    private AnswerKeyLetter[] answerKeyLetters; // all of the correct letters in correct order
    private int currentScore;
    boolean fortunateBadgeCollected;

    /**
     * Constructs a new GameState object
     */
    GameState(Difficulty difficulty) {

        this.difficulty = difficulty;

    }

    void setBalloons(Balloon[] balloons) {
        this.balloons = balloons;
    }


    void setKeyword(String keyword) {
        this.keyword = keyword;
        this.keywordLen = keyword.length();
    }

    void setRemainingBalloons(int numLives) {
        this.remainingBalloons = numLives;
    }

    /**
     * A setter for the correct letters of the correct word to be guessed
     *
     * @param answers an array of AnswerKeyLetter objects that store information about letters in
     *                the correct word
     */
    void setAnswerKeys(AnswerKeyLetter[] answers) {
        this.answerKeyLetters = answers;
    }

    /**
     * Getter for the correct word
     *
     * @return a String object representing the correct word
     */
    String getKeyWord() {
        return this.keyword;
    }

    /**
     * Getter for the current score
     *
     * @return an integer representing the current score
     */
    int getCurrentScore() {
        return this.currentScore;
    }

    @Override
    public boolean collectAdventurousBadge(){
        // only if hard level is selected
        if (this.difficulty.type.equals("Hard")){
            double randomDouble = Math.random();
            return (randomDouble < 0.2);
        }
        return false;
    }

    @Override
    public boolean collectFortunateBadge(){
        // randomly collected
        double randomDouble = Math.random();
        return (randomDouble < 0.1);
    }

    @Override
    public boolean collectStrategicBadge(){
        // randomly collected if there are more than 2 lives left with only 1 letter yet to be guessed
        if (this.currentScore > 30){
            double randomDouble = Math.random();
            return (randomDouble < 0.5);
        }
        return false;
    }


    /**
     * Based on the character guessed, correctness of this letter will be evaluated, and if correct
     * the corresponding letter will be displayed on the screen
     *
     * @param charGuessed the character that is guessed by user by pressing corresponding button
     */
    void updateState(char charGuessed) { // updates the correct letters guessed (if any)
        boolean correct = false;
        //if ((remainingBalloons > 2) && numCorr > (keywordLen - 2)){
         //   fortunateBadgeCollected = collectFortunateBadge();
        //}
        for (int i = 0; i < this.keyword.length(); i++) {
            if (this.answerKeyLetters[i].letter == (charGuessed)) { // a correct letter being guessed
                correct = true;
                this.answerKeyLetters[i].turnBlack(); // shows the letter on screen
                numCorr += 1;
                currentScore += difficulty.correctGuessPoints;
            }
        }
        if (!correct) {
            remainingBalloons -= 1;
            currentScore += difficulty.wrongGuessPoints;
            this.balloons[remainingBalloons].disappear();
        }

    }

}