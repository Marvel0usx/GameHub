package com.example.userinterface.GameManager.HangMan;

import android.view.View;

import java.util.List;

interface HangManView {

    void displayGuess(List<Integer> correctIndex);

    void showHint(View v);

}
