package com.example.userinterface.GameManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.userinterface.R;

public class PracticeMode extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice_mode);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton1:
                toGame(1, true);
                break;
            case R.id.imageButton2:
                toGame(2, true);
                break;
            case R.id.imageButton3:
                toGame(3, true);
                break;
            default:
                break;
        }
    }
}
