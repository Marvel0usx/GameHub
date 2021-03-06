package com.example.userinterface.GameManager.SpaceInvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;


public class SpaceView extends SurfaceView implements SurfaceHolder.Callback {

    public SpaceInvaders Space;
    public MainThread thread;
    public boolean gameOver;

//    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public SpaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    public SpaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    public SpaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Paint paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);


        Space.layout();

        thread.setRunning(true);
        thread.start();

    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

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

    public void setSpace(SpaceInvaders Space) {
        this.Space = Space;
    }

    public MainThread getThread() {
        return thread;
    }

    public void update() {
        if (!Space.isGameOver()) {
            Space.run();
        } else {
            this.gameOver = true;
        }

    }

    public void draw(Canvas canvas) {
        if (canvas != null) {
            super.draw(canvas);
            Space.draw(canvas);
        }
    }
}
