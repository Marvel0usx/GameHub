package com.example.userinterface.GameManager.HangMan;

import java.util.List;

interface HangManView {

    /**
     * displays letters at indexes that have been correctly guessed
     * @param correctIndex list of integers representing correctly-guessed indexes
     */
    void displayGuess(List<Integer> correctIndex);

}
