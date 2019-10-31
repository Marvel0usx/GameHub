package com.example.userinterface.GameManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.userinterface.GameManager.HangMan.HangManActivity;
import com.example.userinterface.GameManager.HangMan.HangManGameActivity;
import com.example.userinterface.GameManager.SpaceInvaders.SpaceActivity;
import com.example.userinterface.GameManager.TowerDefense.TowerDefenseActivity;

import java.io.Serializable;

public class GameManager implements Serializable, Games{
    private User user;
    private Class[] classes;
    private int saved;

    User getUser() {
        return user;
    }

    public GameManager(User user){
        this.user = user;
        classes = new Class[]{MenuActivity.class, HangManActivity.class,TowerDefenseActivity.class,
                SpaceActivity.class};

    }

    public void reLocate(Context context, int saved, int destination){
        this.saved = destination;
        GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity(context);
        gameBackgroundActivity.execute("quit", user, saved);
        user.setLevel(saved);
        Intent intent = new Intent(context,classes[destination]);
        Bundle bundle = new Bundle();
        helper(intent, context, bundle);
    }

    public void toInter(Context context,boolean winOrLose){
        GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity(context);
        if (winOrLose){
            saved++;
        }else {
            saved = 0;
        }
        gameBackgroundActivity.execute("quit", user, saved);
        user.setLevel(saved);
        Intent intent = new Intent(context, NextGame.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("winOrLose", winOrLose);
        helper(intent, context,bundle);
    }

    public void toMenu(Context context){
        Intent intent = new Intent(context, MenuActivity.class);
        Bundle bundle = new Bundle();
        helper(intent,context,bundle);
    }

    public void next(Context context){
        Intent intent = new Intent(context, classes[saved]);
        Bundle bundle = new Bundle();
        helper(intent, context,bundle);
    }


    private void helper(Intent intent, Context context, Bundle bundle){
        bundle.putSerializable("Game", this);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}