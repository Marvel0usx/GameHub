package com.example.userinterface.GameManager;


import android.content.Context;

public interface Games {
    User getUser();

    void reLocate(Context context, int saved, int destination);

    void toInter(Context context, boolean winOrLose);

    void toMenu(Context context);

    void next(Context context);
}
