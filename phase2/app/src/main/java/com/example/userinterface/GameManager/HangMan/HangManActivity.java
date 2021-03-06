package com.example.userinterface.GameManager.HangMan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.userinterface.GameManager.GameActivity;
import com.example.userinterface.R;

import java.io.InputStream;
import java.util.List;

public class HangManActivity extends GameActivity {

    Difficulty diff;
    boolean practiceMode;
    CSVReader csvReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hang_man_main);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            practiceMode = bundle.getBoolean("practice");
            Log.d("message", "0 " + practiceMode);
        } else
            practiceMode = false;
        setEasyBtn();
        setModerateBtn();
        setHardBtn();
    }

    /**
     * sets the button responsible for choosing easy level
     */
    private void setEasyBtn() {
        findViewById(R.id.easyButton).setOnClickListener(v -> {
            InputStream inputStream = getResources().openRawResource(R.raw.easy_word);
            csvReader = new CSVReader(inputStream);
            List<String[]> result = csvReader.read();
            this.diff = new EasyDifficulty(result);
            Intent intent = new Intent(HangManActivity.this,
                    ChooseCharacterActivity.class);
            intent.putExtra("difficulty", diff);
            intent.putExtra("practice", practiceMode);
            startActivity(intent);
        });
    }

    /**
     * sets the button responsible for choosing moderate level
     */
    private void setModerateBtn() {
        findViewById(R.id.moderateButton).setOnClickListener(v -> {
            InputStream inputStream = getResources().openRawResource(R.raw.moderate_word);
            csvReader = new CSVReader(inputStream);
            List<String[]> result = csvReader.read();
            this.diff = new ModerateDifficulty(result);
            Intent intent = new Intent(HangManActivity.this,
                    ChooseCharacterActivity.class);
            intent.putExtra("difficulty", diff);
            intent.putExtra("practice", practiceMode);
            startActivity(intent);
        });
    }

    /**
     * sets the button responsible for choosing hard level
     */
    private void setHardBtn() {
        findViewById(R.id.hardButton).setOnClickListener(v -> {
            InputStream inputStream = getResources().openRawResource(R.raw.hard_word);
            csvReader = new CSVReader(inputStream);
            List<String[]> result = csvReader.read();
            diff = new HardDifficulty(result);
            Intent intent = new Intent(HangManActivity.this,
                    ChooseCharacterActivity.class);
            intent.putExtra("difficulty", diff);
            intent.putExtra("practice", practiceMode);
            startActivity(intent);
        });
    }
}
