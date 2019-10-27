package com.example.userinterface.GameManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.userinterface.GameManager.HangMan.HangManActivity;
import com.example.userinterface.GameManager.HangMan.HangManGameActivity;
import com.example.userinterface.MainActivity;
import com.example.userinterface.R;
import com.example.userinterface.SignupActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnStart = findViewById(R.id.start);
    }

    public void startGame(View v){
        Intent intent = new Intent(MenuActivity.this, HangManActivity.class);
        startActivity(intent);
    }
}
