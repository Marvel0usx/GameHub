package com.example.userinterface.GameManager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userinterface.GameManager.HangMan.HangManActivity;
import com.example.userinterface.GameManager.SpaceInvaders.SpaceActivity;
import com.example.userinterface.GameManager.TowerDefense.TowerDefenseActivity;

/**
 * This is the parent class of all the games in the program.
 * It contains the user and the classes that the game should be going through.
 * it also contains all the methods used by game to get to the next game and store information to
 * the server.
 */
public abstract class GameActivity extends AppCompatActivity {

    private static final Class[] CLASSES = new Class[]{MenuActivity.class, HangManActivity.class, TowerDefenseActivity.class,
            SpaceActivity.class, EndGame.class};
    private static User user = null;
    private static boolean ifLost = false;
    public Context context;

    /**
     * Checks if the player has lost a game
     *
     * @return a boolean of whether or not the user lost the game.
     */
    public static boolean isIfLost() {
        return ifLost;
    }

    /**
     * if the back button on the phone has been pressed.
     * go back to the main menu.
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Go to a specific game.
     *
     * @param saved        the index of the game
     * @param practiceMode whether or not it is in the practice mode
     */
    public void toGame(int saved, boolean practiceMode) {
        ifLost = false;
        if (!practiceMode) {

            GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity();
            user.getStatTracker().setLevel(saved);
            gameBackgroundActivity.execute("quit", user);
        }

        Intent intent = new Intent(context, CLASSES[saved]);
        Log.d("message", "this is the boolean in to game " + practiceMode);
        intent.putExtra("practice", practiceMode);
        startActivity(intent);
    }

    /**
     * Go to the intermediates of the games.
     *
     * @param won          it the user has won the game
     * @param practiceMode if it is in practice mode or not.
     */
    public void goToIntermediate(boolean won, boolean practiceMode) {
        if (!won) {
            ifLost = true;
        }
        if (!practiceMode) {
            GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity();
            if (won) {
                int num = user.getStatTracker().getLevel() + 1;
                user.getStatTracker().setLevel(num);
            } else {
                user.getStatTracker().setCurrScore(0);
                user.getStatTracker().setLevel(0);
                ifLost = true;
            }
            gameBackgroundActivity.execute("quit", user);
        }

        Intent intent = new Intent(context, NextGame.class);
        intent.putExtra("practice", practiceMode);
        Log.d("message", "this is the boolean at to game in go to inter " + practiceMode);
        startActivity(intent);

    }

    /**
     * @return the user of the game
     */
    public User getUser() {
        return user;
    }

    /**
     * set the user for the game
     *
     * @param user the user that is going to be set.
     */
    public static void setUser(User user) {
        if (GameActivity.user == null) {
            GameActivity.user = user;
        }

    }

    /**
     * Go back to the main menu.
     */
    public void toMenu() {
        ifLost = false;
        Intent intent = new Intent(context, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Delete the user that is playing right now, used for sign out.
     */
    public void deleteUser() {
        user = null;
    }

    /**
     * Go to the next game from the intermediate screen.
     *
     * @param view that next button that is pressed,
     */
    public void next(View view) {
        ifLost = false;
        Intent intent = new Intent(context, CLASSES[user.getStatTracker().getLevel()]);
        startActivity(intent);
    }
}
