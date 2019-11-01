package com.example.userinterface.GameManager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.userinterface.BackgroundActivity;
import com.example.userinterface.GameManager.HangMan.HangManActivity;
import com.example.userinterface.GameManager.HangMan.HangManGameActivity;
import com.example.userinterface.GameManager.SpaceInvaders.SpaceActivity;
import com.example.userinterface.GameManager.TowerDefense.TowerDefense;
import com.example.userinterface.GameManager.TowerDefense.TowerDefenseActivity;
import com.example.userinterface.MainActivity;
import com.example.userinterface.R;

import java.io.Serializable;

public class MenuActivity extends Activity {

    Button btnStart;
    Button btnResume;
    Button btnStats;
    GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu);
        btnStart = findViewById(R.id.start);
        btnResume = findViewById(R.id.Resume);
        btnStats = findViewById(R.id.Stats);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            gameManager = (GameManager) bundle.getSerializable("Game");
        }
        if (gameManager != null) {
            if (gameManager.getUser().getLevel()==0){
                btnResume.setEnabled(false);
            }else{
                btnResume.setEnabled(true);
            }
        }
        super.onCreate(savedInstanceState);



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameManager.reLocate(MenuActivity.this, 1, 3);
            }
        });

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (gameManager.getUser().getLevel()){
                    case 1:
                        gameManager.reLocate(MenuActivity.this,1,1);
                        break;
                    case 2:
                        gameManager.reLocate(MenuActivity.this,2,2);
                        break;
                    case 3:
                        gameManager.reLocate(MenuActivity.this,3,3);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    public void Stats(View view) {
        Intent intent = new Intent(MenuActivity.this, Stats.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user",gameManager.getUser());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
