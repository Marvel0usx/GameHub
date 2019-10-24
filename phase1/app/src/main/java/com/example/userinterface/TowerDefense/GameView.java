package com.example.userinterface.TowerDefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    public static String temp="";
    public TowerDefense towerDefense = new TowerDefense();

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);

    }
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    public void update() {
        towerDefense.update();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            towerDefense.draw(canvas);
            Paint paintText = new Paint();
            paintText.setTextSize(70);
            paintText.setTypeface(Typeface.DEFAULT_BOLD);
            paintText.setColor(Color.RED);
            canvas.drawText(temp,400,400,paintText);
        }


    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        TowerDefense.wave1.add(new Minion());
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }
}
