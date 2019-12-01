package com.example.userinterface.GameManager.HangMan;

import android.view.View;

public class HangManPresenter {

    HangManView view;
    GameState gameState;

    HangManPresenter(HangManView view, GameState gameState){
        this.gameState = gameState;
        this.view = view;
    }

    void makeGuess(Character charGuessed){
        this.gameState.updateState(charGuessed);
    }

}
