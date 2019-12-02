package com.example.userinterface.GameManager.SpaceInvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

import com.example.userinterface.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class SpacePresenter extends SurfaceView implements SurfaceHolder.Callback {

    public SpaceInvaders Space;
    public MainThread thread;
    public boolean gameOver;
    private int wave = 0;
    private SpaceView vSpace;
    private ScoreBoardView vScore;
    private BackgroudView vBackground;
    private List<Pair<Bitmap, Rect>> processedObjs;
    private Map<Class, Bitmap> appearance = new Hashtable<>();

    public SpacePresenter(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        vSpace = new SpaceView();
        vScore = new ScoreBoardView();
        vBackground = new BackgroudView();
    }

    public SpacePresenter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        vSpace = new SpaceView();
        vScore = new ScoreBoardView();
        vBackground = new BackgroudView();
    }

    public SpacePresenter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        vSpace = new SpaceView();
        vScore = new ScoreBoardView();
        vBackground = new BackgroudView();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Paint paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);

        Space.layout();
        initializingAppearanceMap();
        thread.setRunning(true);
        thread.start();
    }

    /**
     * Helper method that creates a dictionary for matching Model with its View
     */
    private void initializingAppearanceMap() {
        Bitmap blueBullet = BitmapFactory.decodeResource(getResources(), R.drawable.bluebullet);
        Bitmap redBullet = BitmapFactory.decodeResource(getResources(), R.drawable.redbullet);
        Bitmap player = BitmapFactory.decodeResource(getResources(), R.drawable.space_player);
        Bitmap boss = BitmapFactory.decodeResource(getResources(), R.drawable.space_boss);
        Bitmap enemy = BitmapFactory.decodeResource(getResources(), R.drawable.space_troop);

        appearance.put(PlayerBullet.class, blueBullet);
        appearance.put(EnemyBullet.class, redBullet);
        appearance.put(Player.class, player);
        appearance.put(Enemy.class, enemy);
        appearance.put(Boss.class, boss);
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
            processedObjs = parse(Space.getUpdate());
        } else {
            this.gameOver = true;
        }
    }

    private void configBackground(){
        Bitmap bmp = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), getScene(wave)));
        vBackground.configBackground(bmp, getWidth(), getHeight());
    }

    int getScene(int wave){
        switch (wave){
            case 1:
                return R.drawable.space_bg01;
            case 2:
                return R.drawable.space_bg02;
            case 3:
                return R.drawable.space_bg03;
            case 4:
                return R.drawable.space_bg04;
            case 5:
                return R.drawable.space_bg05;
            default:
                return R.drawable.space_bg06;
        }
    }

    public void draw(Canvas canvas) {
        if (canvas != null) {
            super.draw(canvas);
            if (wave != Space.getWave()){
                configBackground();
                wave = Space.getWave();
            }
            vBackground.draw(canvas);
            vSpace.draw(canvas, processedObjs);
            vScore.draw(canvas, Space.getScoreboard());
            }
    }

    /**
     * Parses from a list of SpaceObjects to create a list of Bitmap and Coordinate pairs to send
     * to the View, allowing an image to be drawn
     *
     * @param spaceObjectList a list of spaceObjects
     * @return a list of organized Bitmap Rect pairs
     */
    private List<Pair<Bitmap, Rect>> parse(List<SpaceObject> spaceObjectList) {
        List<Pair<Bitmap, Rect>> parsed = new ArrayList<>();
        Bitmap bmp;
        for (SpaceObject item : spaceObjectList) {
            Rect rect = new Rect(item.getX(), item.getY(), item.getX() + item.getSize(),
                    item.getY() + item.getSize());
            bmp = appearance.get(item.getClass());
            Pair<Bitmap, Rect> pair = new Pair<>(bmp, rect);
            parsed.add(pair);
        }
        return parsed;
    }
}
