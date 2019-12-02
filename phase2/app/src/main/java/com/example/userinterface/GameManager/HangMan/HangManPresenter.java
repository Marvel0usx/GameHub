package com.example.userinterface.GameManager.HangMan;

class HangManPresenter {

    private HangManView view;
    private GameState gameState;

    HangManPresenter(HangManView view, GameState gameState){
        this.gameState = gameState;
        this.view = view;
    }

    void makeGuess(Character charGuessed){
        this.gameState.updateState(charGuessed);
    }

}
