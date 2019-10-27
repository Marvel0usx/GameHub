package com.example.userinterface.HangMan;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;
import java.util.Random;
import com.example.userinterface.R;
import android.widget.GridView;

public class HangManGameActivity extends Activity{
    private ImageView[] balloons; // balloon images
    private int numLives = 6; // number of lives
    private int currLen; // number of characters in current word
    private int remainingBallons; // number of balloons left, decreases each time a wrong letter is guessed
    private int numCorr; // number of letters correctly guessed

    private String[] words;
    private Random rand;
    private String currentWord;
    private LinearLayout wordLayout;
    private TextView[] characterViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_activity_game);
        balloons = new ImageView[numLives];
        // initializes images containing different numbers of ballons, representing different number
        // of lives.
        rand = new Random();
        currentWord = "";
        wordLayout = findViewById(R.id.word);

        balloons[0] = findViewById(R.id.ballon1);
        balloons[1] = findViewById(R.id.ballon2);
        balloons[2] = findViewById(R.id.ballon3);
        balloons[3] = findViewById(R.id.ballon4);
        balloons[4] = findViewById(R.id.ballon5);
        balloons[5] = findViewById(R.id.ballon6);
        playHangMan();
    }

    public void displayBalloons(View v){

    }


    public void makeGuess(View v){
        // the user has clicked on the letter he/she wants to guess
        String letterGuessed = ((TextView)v).getText().toString();
        char charGuessed = letterGuessed.charAt(0);
        v.setEnabled(false);
        v.setBackgroundResource(R.drawable.hm_letter_clicked);

        boolean correct = false;

        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == charGuessed) {
                correct = true;
                numCorr++;
                characterViews[i].setTextColor(Color.BLACK);
            }
        }
            if (correct) {
                // correct guess
                if (numCorr == currLen){
                    // won the game
                    AlertDialog.Builder winMsg = new AlertDialog.Builder(this);
                    winMsg.setTitle("YAY");
                    winMsg.setMessage("You win! Congratulations!");
                    winMsg.setNegativeButton("Exit",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    HangManGameActivity.this.finish();
                                }});

                    winMsg.show();
                }

            } else if (remainingBallons > 0){
                // player still has lives left

                balloons[remainingBallons - 1].setVisibility(v.INVISIBLE);
                remainingBallons = remainingBallons - 1;


            } else{
                // player has lost
                AlertDialog.Builder loseMsg = new AlertDialog.Builder(this);
                loseMsg.setTitle("OOPS :(");
                loseMsg.setMessage("You lose!");
            }
            }

    private void playHangMan() {
        // plays a new HangMan game
       // String newWord = words[rand.nextInt(words.length)];
        currentWord = "COMPUTER";
        characterViews = new TextView[currentWord.length()];
        wordLayout.removeAllViews();

//        for(int p = 0; p < balloons.length; p++) {
//            balloons[p].setVisibility(View.VISIBLE);
//        }


        for (int c = 0; c < currentWord.length(); c++) {
            characterViews[c] = new TextView(this);
            characterViews[c].setText(""+currentWord.charAt(c));
        }

        remainingBallons = 6;
        currLen = currentWord.length();
        numCorr = 0;

        for (int c = 0; c < currentWord.length(); c++) {
            characterViews[c] = new TextView(this);
            characterViews[c].setText(""+currentWord.charAt(c));
            characterViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            characterViews[c].setGravity(Gravity.CENTER);
            characterViews[c].setTextColor(Color.WHITE);
            characterViews[c].setBackgroundResource(R.drawable.hm_letter_background);
            //add to layout
            wordLayout.addView(characterViews[c]);
        }

    }

}
