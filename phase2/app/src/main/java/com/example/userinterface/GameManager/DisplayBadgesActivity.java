package com.example.userinterface.GameManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.userinterface.R;

public class DisplayBadgesActivity extends GameActivity {

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

    @SuppressLint("SetTextI18n")
    protected void showBadgesCount() {
        numFortune.setText(user.getStatTracker().getFortuneNum() + "");
        numStrategy.setText(user.getStatTracker().getStrategicNum() + "");
        numAdventurous.setText(user.getStatTracker().getAdventurousNum() + "");
    }
}
