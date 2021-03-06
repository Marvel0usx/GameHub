package com.example.userinterface.GameManager.SpaceInvaders;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.example.userinterface.GameManager.*;
import com.example.userinterface.R;

public class SpaceActivity extends Activity implements VariableChangeListener{

    SpaceInvaders Space;
    SpaceView spaceView;
    Button btnLeft, btnRight;
    Games gameManager;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Space = new SpaceInvaders(screenWidth, screenHeight);
        setContentView(R.layout.activity_space);
        spaceView = findViewById(R.id.spaceView);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        spaceView.setSpace(Space);
        Space.setVariableChangeListener(this);

        if (bundle != null) {
            gameManager = (Games) bundle.getSerializable("Game");
        }
        if (spaceView != null) {
            spaceView.setSpace(Space);
        }

        btnLeft = findViewById(R.id.Left);
        btnRight = findViewById(R.id.Right);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Left:
                spaceView.Space.goLeft();
                break;
            case R.id.Right:
                spaceView.Space.goRight();
                break;
            default:
                break;
        }
    }

    public void onBackPressed(){
        spaceView.getThread().setRunning(false);
        gameManager.toMenu(this);
    }
    @Override
    public void onVariableChange(boolean spaceGame){
        spaceView.getThread().setRunning(false);
        StatTracker statTracker = gameManager.getUser().getStatTracker();
        statTracker.addToCurrScore(Space.getScore());
        gameManager.toInter(SpaceActivity.this, Space.isWin());
        System.out.println("good");

    }

}
