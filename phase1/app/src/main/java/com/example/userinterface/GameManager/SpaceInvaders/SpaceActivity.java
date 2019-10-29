package com.example.userinterface.GameManager.SpaceInvaders;

import android.app.Activity;
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
    int width, height;
    Button btnLeft, btnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new SpaceView(this));
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Left:
                break;
            case R.id.Right:
                break;
            default:
                break;
        }

    }
}
