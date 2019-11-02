package com.example.userinterface.GameManager.HangMan;

public class GameState {

    protected int keywordLen; // number of characters in current word
    protected int remainingBalloons; // number of balloons left, decreases each time a wrong letter is guessed
    protected int numCorr; // number of letters correctly guessed
    protected String keyword;
    private Balloon[] balloons; // an array of Balloon objects
    private AnswerKeyLetter[] answerKeyLetters; // all of the correct letters in correct order
    protected int currentScore;

    /**
     * Constructs a new GameState object
     * @param keyWord the correct word to be guessed
     * @param balloons all Balloon objects whose images that will be displayed
     */
    public GameState(String keyWord, Balloon[] balloons) {
        this.keyword = keyWord;
        this.keywordLen = keyword.length();
        remainingBalloons = 6;
        this.balloons = balloons;
    }

    /**
     * A setter for the correct letters of the correct word to be guessed
     * @param answers an array of AnswerKeyLetter objects that store information about letters in
     *                the correct word
     */
    protected void setAnswerKeys(AnswerKeyLetter[] answers){
        this.answerKeyLetters = answers;
    }

    /**
     * Getter for the correct word
     * @return a String object representing the correct word
     */
    public String getKeyWord(){
        return this.keyword;
    }

    /**
     * Getter for the current score
     * @return an integer representing the current score
     */
    protected int getCurrentScore() { return this.currentScore; }

    /**
     * Based on the character guessed, correctness of this letter will be evaluated, and if correct
     * the corresponding letter will be displayed on the screen
     * @param charGuessed the character that is guessed by user by pressing corresponding button
     */
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