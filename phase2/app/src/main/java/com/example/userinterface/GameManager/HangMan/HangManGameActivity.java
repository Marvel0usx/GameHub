package com.example.userinterface.GameManager.HangMan;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.userinterface.GameManager.GameActivity;
import com.example.userinterface.GameManager.User;
import com.example.userinterface.R;

import static android.view.View.VISIBLE;


public class HangManGameActivity extends GameActivity implements HangManView{
    GameState gameState;
    MediaPlayer mediaPlayer;
    int currentScore;
    ImageView[] balloons;
    Difficulty difficulty;
    String character;
    User user;
    View rootView;
    View view;
    boolean practiceMode;
    HangManPresenter hangManPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rootView = findViewById(android.R.id.content).getRootView();
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hm_activity_game);
        setMediaPlayer();
        Intent intent = getIntent();
        user = getUser();
        difficulty = (Difficulty) intent.getSerializableExtra("difficulty");
        setDifficulty();
        character = (String) intent.getSerializableExtra("character");
        int resID = getResources().getIdentifier(character, "id", getPackageName());
        ImageView characterImage = findViewById(resID);
        characterImage.setVisibility(VISIBLE);
        setGameState();
        setAnswerKey();
    }

    @Override
    /**
     * Guesses a new letter
     *
     * @param v View object
     */
    public void makeGuess(View v) {
        String letterGuessed = ((TextView) v).getText().toString();
        char charGuessed = letterGuessed.charAt(0);
        v.setEnabled(false);
        v.setBackgroundResource(R.drawable.hm_letter_clicked);
        // updates the gameState by calling the updateState method
        this.hangManPresenter.makeGuess(charGuessed);
        this.currentScore = gameState.getCurrentScore();
        TextView scoreNumberDisplay = findViewById(R.id.scoreNumberDisplay);
        scoreNumberDisplay.setText(Integer.toString(this.currentScore));
        Boolean fortunate = gameState.collectFortunateBadge();
        if (fortunate) {showFortunateBadge(rootView);}
        endGame();
    }

    /**
     * Based on whether all letters habve been guessed, decides whether the game is won. If won,
     * currentScore is updated. This game is finished.
     */
    public void endGame() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            practiceMode = bundle.getBoolean("practice");
            Log.d("message", "0 " + practiceMode);
        } else
            practiceMode = false;
        Log.d("message", "1 " + practiceMode);

        if (gameState.numCorr == gameState.keywordLen) {
            // if all letters have been guessed, wins the game
            mediaPlayer.stop();
            this.currentScore += 100;
            if (!practiceMode)
                user.getStatTracker().addToCurrScore(currentScore);
            goToIntermediate(true, practiceMode);
            Log.d("message", "2 " + practiceMode);
            HangManGameActivity.this.finish();
        } else if (gameState.remainingBalloons == 0) {
            // loses the game if all lives are used up/balloons have disappeared.
            mediaPlayer.stop();
            goToIntermediate(false, practiceMode);
            Log.d("message", "3 " + practiceMode);
            HangManGameActivity.this.finish();
        }
    }

    public Balloon[] showBalloons() {
        int numLives = difficulty.getNumLives();
        balloons = new ImageView[numLives];
        Balloon[] temp = new Balloon[numLives];
        for (int i = 0; i < numLives; i++) {
            String balloonId = "ballon" + (i + 1);
            int resID = getResources().getIdentifier(balloonId, "id", getPackageName());
            balloons[i] = findViewById(resID);
            temp[i] = new Balloon(balloons[i]);
        }
        return temp;
    }

    public void showHint(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.hm_hint_popup, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        boolean focusable = true; // lets taps outside the popup also dismiss it
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        ((TextView)popupWindow.getContentView().findViewById(R.id.hintPopUp)).setText("HINT: " + difficulty.hint);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    public void showFortunateBadge(View view){
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.fortunate_badge, null);

            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;

            boolean focusable = true; // lets taps outside the popup also dismiss it
            PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            ((TextView)popupWindow.getContentView().findViewById(R.id.fortunateBadge)).setText("A Fortunate Badge has been earned!");


        popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return true;
                }
            });
        }


    void setDifficulty(){
        difficulty.setWord();
        difficulty.setNumLives();
    }

    void setGameState(){
        // initialize each Balloon object
        Balloon[] tempBalloons = showBalloons();
        // initialize a new GameState object for this round
        gameState = new GameState(difficulty);
        gameState.setKeyword(difficulty.keyword);
        gameState.setBalloons(tempBalloons);
        gameState.setRemainingBalloons(difficulty.numLives);
    }

    void setMediaPlayer(){
        mediaPlayer = MediaPlayer.create(this, R.raw.hm_background_music);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    void setAnswerKey(){
        LinearLayout wordLayout = findViewById(R.id.word);
        wordLayout.removeAllViews();
        String keyword = gameState.getKeyWord();
        // an array that stores all letters of the correct word
        AnswerKeyLetter[] answerKey = new AnswerKeyLetter[keyword.length()];
        // each letter of the correct word is represented as a TextView object
        this.hangManPresenter = new HangManPresenter(this, gameState);
        TextView[] characterViews = new TextView[keyword.length()];
        for (int c = 0; c < keyword.length(); c++) {
            answerKey[c] = new AnswerKeyLetter(keyword.charAt(c)); // makes a new AnswerKeyLetter
            characterViews[c] = new TextView(this); // creates a TextView object
            characterViews[c].setText("" + keyword.charAt(c));
            characterViews[c].setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            characterViews[c].setGravity(Gravity.CENTER);
            // set it to be white so it does not show up against the white background
            characterViews[c].setTextColor(Color.WHITE);
            characterViews[c].setBackgroundResource(R.drawable.hm_letter_background);
            answerKey[c].addTextView(characterViews[c]);
            wordLayout.addView(answerKey[c].getTextView());
        }

        gameState.setAnswerKeys(answerKey);
    }
}
