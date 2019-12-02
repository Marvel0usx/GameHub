package com.example.userinterface.GameManager.HangMan;

import android.annotation.SuppressLint;
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

import java.util.List;

import static android.view.View.VISIBLE;


public class HangManGameActivity extends GameActivity implements HangManView{
    GameState gameState;
    Integer remainingBalloons;
    MediaPlayer mediaPlayer;
    int currentScore;
    ImageView[] balloons;
    Difficulty difficulty;
    String character;
    User user;
    View rootView;
    boolean practiceMode;
    boolean adventurousBadgeCollected;
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
        remainingBalloons = this.gameState.remainingBalloons;
        setAnswerKey();
    }

    @SuppressLint("SetTextI18n")

    /*
     * Guesses a new letter
     * @param v View object
     */
    public void makeGuess(View v) {
        boolean adventurous = gameState.collectAdventurousBadge();
        if (adventurous && !adventurousBadgeCollected){
            showAdventurousBadge(rootView);
            adventurousBadgeCollected = true;
        }
        boolean fortunate = gameState.collectFortunateBadge();
        if (fortunate){
            showFortunateBadge(rootView);
        }
        String letterGuessed = ((TextView) v).getText().toString();
        char charGuessed = letterGuessed.charAt(0);
        v.setEnabled(false);
        v.setBackgroundResource(R.drawable.hm_letter_clicked);
        this.hangManPresenter.makeGuess(charGuessed);
        this.currentScore = gameState.getCurrentScore();
        TextView scoreNumberDisplay = findViewById(R.id.scoreNumberDisplay);
        scoreNumberDisplay.setText(Integer.toString(this.currentScore));
        boolean strategic = gameState.collectStrategicBadge();
        if (strategic) {showStrategicBadge(rootView);}
        endGame();
    }

    /**
     * displays all letters that were guessed correctly from the most recent guess
     * @param correctIndex list of integers representing all indexes of the word that have been
     *                     correctly guessed
     */
    @Override
    public void displayGuess(List<Integer> correctIndex) { // updates the correct letters guessed (if any)
        if (correctIndex != null){
            for (int i = 0; i < correctIndex.size(); i++) {
                int index = correctIndex.get(i);
                this.gameState.answerKeyLetters[index].turnBlack(); // shows the letter on screen
            }
        }
        else {
            gameState.balloons[gameState.remainingBalloons].disappear();
        }

    }

    /**
     * Based on whether all letters have been guessed, decides whether the game is won. If won,
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

    /**
     * shoows all balloons before any letters have been guessed
     * @return list of Balloon objects
     */
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

    /**
     * shows the Hint on the screen
     * @param view a vew object
     */
    public void showHint(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.hm_hint_popup, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        boolean focusable = true; // lets taps outside the popup also dismiss it
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        ((TextView)popupWindow.getContentView().findViewById(R.id.hintPopUp)).setText("HINT: " + difficulty.hint);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener((v, event) -> {
            popupWindow.dismiss();
            return true;
        });
    }

    /**
     * notifies the user that a strategic badge has been collected
     * @param view the view object of the game
     */
    public void showStrategicBadge(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.badge_popup_window, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        boolean focusable = true; // lets taps outside the popup also dismiss it
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        ((TextView)popupWindow.getContentView().findViewById(R.id.badge)).setText("A Strategic Badge has been earned!");
        if (!practiceMode)
            user.getStatTracker().addToBadges(false, true, false);

        popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return true;
                }
            });
        }

    /**
     * notifies the user that an adventurous badge has been collected
     * @param view the view object of the game
     */
    public void showAdventurousBadge(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.badge_popup_window, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        boolean focusable = true; // lets taps outside the popup also dismiss it
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        ((TextView)popupWindow.getContentView().findViewById(R.id.badge)).setText("An Adventurous Badge has been earned!");
        if (!practiceMode)
            user.getStatTracker().addToBadges(false, false, true);


        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    /**
     * notifies the user that a fortunate badge has been collected
     * @param view the view object of the game
     */
    public void showFortunateBadge(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.badge_popup_window, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        boolean focusable = true; // lets taps outside the popup also dismiss it
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        ((TextView)popupWindow.getContentView().findViewById(R.id.badge)).setText("A Fortunate Badge has been earned!");
        if(!practiceMode)
            user.getStatTracker().addToBadges(true, false, false);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    /**
     * set the difficulty level and corresponding number of lives
     */
    void setDifficulty(){
        difficulty.setWord();
        difficulty.setNumLives();
    }

    /**
     * set the attributes of game state
     */
    void setGameState(){
        // initialize each Balloon object
        Balloon[] tempBalloons = showBalloons();
        // initialize a new GameState object for this round
        gameState = new GameState(difficulty);
        gameState.setKeyword(difficulty.keyword);
        gameState.setBalloons(tempBalloons);
        gameState.setRemainingBalloons(difficulty.numLives);
    }

    /**
     * Set the media player
     */
    void setMediaPlayer(){
        mediaPlayer = MediaPlayer.create(this, R.raw.hm_background_music);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    /**
     * set all answer keys (correct letters)
     */
    @SuppressLint("SetTextI18n")
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

    /**
     * goes back menu page
     */
    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        super.onBackPressed();
    }
}
