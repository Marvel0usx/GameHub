package com.example.userinterface.GameManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.userinterface.GameManager.HangMan.HangManActivity;
import com.example.userinterface.GameManager.SpaceInvaders.SpaceActivity;
import com.example.userinterface.GameManager.TowerDefense.TowerDefenseActivity;
import com.example.userinterface.R;

public class MenuActivity extends AppCompatActivity {

    Button btnStart;
    Button btnResume;
    Button btnStats;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnStart = findViewById(R.id.start);
        btnResume = findViewById(R.id.Resume);
        btnStats = findViewById(R.id.Stats);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            user = (User)bundle.getSerializable("User");
        }
        if (user != null) {
            if (user.getLevel() == 0){
                btnResume.setEnabled(false);
            }else{
                btnResume.setEnabled(true);
            }
        }
        GameBackgroundActivity backgroundActivity = new GameBackgroundActivity(MenuActivity.this);

    }

    public void startGame(View v){
        Intent intent = new Intent(MenuActivity.this, TowerDefenseActivity.class);
        intent.putExtras(patch());
        startActivity(intent);
    }
    public void Resume(View v){
        Intent intent = null;
        switch (user.getLevel()){
            case 1:
                intent = new Intent(MenuActivity.this, HangManActivity.class);
                break;
            case 2:
                intent = new Intent(MenuActivity.this, TowerDefenseActivity.class);
                break;
            case 3:
                intent = new Intent(MenuActivity.this, SpaceActivity.class);
                break;
            default:
                break;
        }
        if (intent != null) {
            intent.putExtras(patch());
        }
        startActivity(intent);
    }

    public void Stats(View view) {
        Intent intent = new Intent(MenuActivity.this, Stats.class);
        startActivity(intent);
    }

    public Bundle patch(){
        Bundle bundle = new Bundle();
        bundle.putSerializable("User",user);
        return bundle;
    }
}
