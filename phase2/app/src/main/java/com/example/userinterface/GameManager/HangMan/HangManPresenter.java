package com.example.userinterface.GameManager.HangMan;

import java.util.List;

class HangManPresenter {

    private HangManView view;
    private GameState gameState;

    HangManPresenter(HangManView view, GameState gameState){
        this.gameState = gameState;
        this.view = view;
    }

    void makeGuess(Character charGuessed){
        List<Integer> correctIndex = this.gameState.updateState(charGuessed);
        this.view.displayGuess(correctIndex);
    }

}
