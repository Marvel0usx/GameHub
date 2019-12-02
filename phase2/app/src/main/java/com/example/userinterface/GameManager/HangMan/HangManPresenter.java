package com.example.userinterface.GameManager.HangMan;

import java.util.List;

class HangManPresenter {

    private HangManView view;
    private GameState gameState;

    /**
     * creates a new HangManPresenter
     * @param view game's view
     * @param gameState gameState object responsible for updating different attributes of the game
     */
    HangManPresenter(HangManView view, GameState gameState){
        this.gameState = gameState;
        this.view = view;
    }

    /**
     * updates the letters being displayed based on whether the guess is correct
     * @param charGuessed character the user guessed
     */
    void makeGuess(Character charGuessed){
        List<Integer> correctIndex = this.gameState.updateState(charGuessed);
        this.view.displayGuess(correctIndex);
    }

}
