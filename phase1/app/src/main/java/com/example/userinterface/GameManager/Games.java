package com.example.userinterface.GameManager;


import android.content.Context;

public interface Games {
    void reLocate(Context context, int saved, int destination);

    void toInter(Context context, boolean winOrLose);

    void toMenu(Context context);
}
