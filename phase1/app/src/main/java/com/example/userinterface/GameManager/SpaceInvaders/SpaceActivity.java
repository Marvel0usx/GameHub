package com.example.userinterface.GameManager.SpaceInvaders;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Space;

import com.example.userinterface.R;

public class SpaceActivity extends Activity {

    SpaceInvaders Space;
    Button btnLeft, btnRight;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Space = new SpaceInvaders(screenWidth, screenHeight);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        SpaceView spaceView = new SpaceView(this);
        spaceView.setSpace(Space);
        setContentView(spaceView);
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
