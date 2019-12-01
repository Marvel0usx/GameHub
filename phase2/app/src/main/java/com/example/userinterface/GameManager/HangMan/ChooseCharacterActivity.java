package com.example.userinterface.GameManager.HangMan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.userinterface.GameManager.GameActivity;
import com.example.userinterface.R;
import java.io.Serializable;

public class ChooseCharacterActivity extends GameActivity implements Serializable{

    String character;
    Difficulty difficulty;
    boolean practiceMode;

    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_choose_char);
        showCharButtons();
        Intent difficultyLevel = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            practiceMode = bundle.getBoolean("practice");
            Log.d("message", "0 " + practiceMode);
        } else
            practiceMode = false;

        this.difficulty = (Difficulty) difficultyLevel.getSerializableExtra("difficulty");
    }

    public void showCharButtons(){
        String[] characters = {"charButton1", "charButton2", "charButton3", "charButton4",
                "charButton5", "charButton6"};
        String[] characterImages = {"charA", "charB", "charC", "charD", "charE", "charF"};
        for (int i = 0; i < characters.length; i++){
            int resID = getResources().getIdentifier(characters[i], "id", getPackageName());
            int finalI = i;
            findViewById(resID).setOnClickListener(v -> {
                this.character = characterImages[finalI];
                Intent intent = new Intent(ChooseCharacterActivity.this, HangManGameActivity.class);
                intent.putExtra("character", character);
                intent.putExtra("difficulty", difficulty);
                intent.putExtra("practice", practiceMode);
                startActivity(intent);
            });
        }
    }
}
