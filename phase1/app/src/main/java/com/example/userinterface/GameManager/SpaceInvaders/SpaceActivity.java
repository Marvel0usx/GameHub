package com.example.userinterface.GameManager.SpaceInvaders;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.userinterface.GameManager.MenuActivity;
import com.example.userinterface.GameManager.*;
import com.example.userinterface.R;

public class SpaceActivity extends Activity {

    SpaceInvaders Space;
    Button btnLeft, btnRight, btnExit;
    GameManager gameManager;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Space = new SpaceInvaders(screenWidth, screenHeight);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        SpaceView spaceView = new SpaceView(this);
        spaceView.setSpace(Space);
        setContentView(R.layout.activity_space);

        btnLeft = findViewById(R.id.Left);
        btnRight = findViewById(R.id.Right);
        btnExit = findViewById(R.id.Exit);

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Left:
                Space.goLeft();
            case R.id.Right:
                Space.goRight();
            default:
                break;
        }

    }

}
