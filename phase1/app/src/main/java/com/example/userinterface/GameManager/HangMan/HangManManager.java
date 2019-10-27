package com.example.userinterface.GameManager.HangMan;


import java.util.HashMap;


public abstract class HangManManager{

    HashMap<Integer, String> keyword = new HashMap();


    int numberOfGuesses;

    abstract Integer getRemainingGuesses();

    abstract Integer getGuessesUsed();


    abstract boolean alreadyGuessed(char guessedLetter);
    // checks if the input letter has been already guessed or not

    abstract String getGuessedLetters();

    void makeNewGuess(){

    };




}
