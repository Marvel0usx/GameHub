package com.example.userinterface.GameManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.userinterface.GameManager.HangMan.Difficulty;
import com.example.userinterface.R;

public class DisplayBadgesActivity extends GameActivity{

    TextView numFortune, numStrategy, numAdventurous;
    User user;

    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_badges);
        numFortune = findViewById(R.id.fortuneCount);
        numStrategy = findViewById(R.id.strategicCount);
        numAdventurous = findViewById(R.id.adventurousCount);
        user = getUser();
        showBadgesCount();
    }

    protected void showBadgesCount(){
        numFortune.setText(user.getStatTracker().getFortuneNum() + "");
        numStrategy.setText(user.getStatTracker().getStrategicNum() + "");
        numAdventurous.setText(user.getStatTracker().getAdventurousNum() + "");
    }
}
