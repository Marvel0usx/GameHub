package com.example.userinterface.GameManager.HangMan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.userinterface.GameManager.GameActivity;
import com.example.userinterface.R;

import java.io.Serializable;

public class ChooseCharacterActivity extends GameActivity implements Serializable {

    Character character;

    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_choose_char);

        setCharA();
        setCharB();
        setCharC();
        setCharD();
        setCharE();
        setCharF();

    }

    private void setCharA(){
        findViewById(R.id.charButton1).setOnClickListener(v -> {
            this.character = new Character(findViewById(R.id.charButton1));
            Intent i = new Intent(ChooseCharacterActivity.this, HangManGameActivity.class);
            i.putExtra("character", character);
            startActivity(i);
        });
    }

    private void setCharB(){
        findViewById(R.id.charButton2).setOnClickListener(v -> {
            this.character = new Character(findViewById(R.id.charButton2));
            Intent i = new Intent(ChooseCharacterActivity.this, HangManGameActivity.class);
            i.putExtra("character", character);
            startActivity(i);
        });
    }

    private void setCharC(){
        findViewById(R.id.charButton3).setOnClickListener(v -> {
            this.character = new Character(findViewById(R.id.charButton3));
            Intent i = new Intent(ChooseCharacterActivity.this, HangManGameActivity.class);
            i.putExtra("character", character);
            startActivity(i);
        });
    }

    private void setCharD(){
        findViewById(R.id.charButton4).setOnClickListener(v -> {
            this.character = new Character(findViewById(R.id.charButton4));
            Intent i = new Intent(ChooseCharacterActivity.this, HangManGameActivity.class);
            i.putExtra("character", character);
            startActivity(i);
        });
    }

    private void setCharE(){
        findViewById(R.id.charButton5).setOnClickListener(v -> {
            this.character = new Character(findViewById(R.id.charButton5));
            Intent i = new Intent(ChooseCharacterActivity.this, HangManGameActivity.class);
            i.putExtra("character", character);
            startActivity(i);
        });
    }

    private void setCharF(){
        findViewById(R.id.charButton6).setOnClickListener(v -> {
            this.character = new Character(findViewById(R.id.charButton6));
            Intent i = new Intent(ChooseCharacterActivity.this, HangManGameActivity.class);
            i.putExtra("character", character);
            startActivity(i);
        });
    }





}
