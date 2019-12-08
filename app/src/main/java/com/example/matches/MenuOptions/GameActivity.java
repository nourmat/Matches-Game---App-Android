package com.example.matches.MenuOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.matches.Levels.Game_First_level;
import com.example.matches.Levels.Game_Fourth_level;
import com.example.matches.Levels.Game_Second_level;
import com.example.matches.Levels.Game_Third_level;
import com.example.matches.MainActivity;
import com.example.matches.Objects.Card;
import com.example.matches.Objects.OnDataPass;
import com.example.matches.R;
import com.example.matches.ScoreFragment;

public class GameActivity extends AppCompatActivity implements Game_First_level.OnFragmentInteractionListener, ScoreFragment.OnFragmentInteractionListener, OnDataPass {

    private static final int LEVEL_NUMBER = 4;

    private Button btnBack, btnReset;
    private TextView textViewTime;
    private int timeMin, timeSec;
    private Thread threadTimer; //used to run timer in background
    private boolean timerOn = false; //used to control timer
    private int currentLevel;
    private boolean isContinueLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        currentLevel = intent.getIntExtra(MainActivity.LEVEL_MESSAGE,1);
        isContinueLevels = intent.getBooleanExtra(MainActivity.ISCONTINUE_MESSAGE, false);

       initLevel(currentLevel);
    }

    public void init(final int level){

        btnBack = findViewById(R.id.btnBack);
        btnReset = findViewById(R.id.btnReset);
        textViewTime = findViewById(R.id.textViewTime);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initGameFragment(level);
            }
        });
    }

    public void initLevel (int level){
        init(level);
        initGameFragment(level);
    }

    public void initGameFragment(int level){
        stopTimer();
        removeExistingFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (level) {
            case 1:
                if (findViewById(R.id.fragment_container) != null) {
                    Game_First_level firstLevel = new Game_First_level();
                    fragmentTransaction.replace(R.id.fragment_container, firstLevel);
                    fragmentTransaction.commit();
                    startTimer();
                }
                break;
            case 2:
                if (findViewById(R.id.fragment_container) != null) {
                    Game_Second_level secondLevel = new Game_Second_level();
                    fragmentTransaction.replace(R.id.fragment_container, secondLevel);
                    fragmentTransaction.commit();
                    startTimer();
                }
                break;
            case 3:
                if (findViewById(R.id.fragment_container) != null) {
                    Game_Third_level thirdLevel = new Game_Third_level();
                    fragmentTransaction.replace(R.id.fragment_container, thirdLevel);
                    fragmentTransaction.commit();
                    startTimer();
                }
                break;
            case 4:
                if (findViewById(R.id.fragment_container) != null) {
                    Game_Fourth_level fourthlevel = new Game_Fourth_level();
                    fragmentTransaction.replace(R.id.fragment_container, fourthlevel);
                    fragmentTransaction.commit();
                    startTimer();
                }
                break;
        }
    }

    public void removeExistingFragment(){
        if (Card.mediaPlayer != null)//make sure no sound is playing
            Card.mediaPlayer.stop();

        //prepare fragment objects
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment != null)
            fragmentTransaction.remove(fragment).commit();
    }

    @SuppressLint("StaticFieldLeak")
    public void startTimer(){
        timeMin = 0;
        timeSec = 0;
        timerOn = true;

        threadTimer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (timerOn) {
                    try { Thread.sleep(1000);}catch (Exception e){}
                    timeSec++;
                    if (timeSec == 60) {
                        timeSec = 0;
                        timeMin++;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textViewTime.setText(timeMin+":"+timeSec);
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewTime.setText("0:0");//after stopping the timer reset
                    }
                });
            }
        });
        threadTimer.start();
    }

    public void stopTimer(){
        timerOn = false;
        timeMin = 0;
        timeSec = 0;
        if(threadTimer != null) threadTimer.interrupt();
    }

    public void showScore(){
        int score = 1000-(timeMin * 100 + timeSec * 10);
        score = score > 0 ? score : 0;

        Bundle bundle = new Bundle();
        bundle.putInt(ScoreFragment.SCOREMESAGE,score);
        ScoreFragment scoreFragment = new ScoreFragment();
        scoreFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, scoreFragment).commit();
    }

    /**
     * called when game finished
     * @param value passes 1 when game finished correctly
     */
    @Override
    public void onDataPass(int value) {
        removeExistingFragment(); //stop music and remove fragment
        showScore(); //calc score and open new fragment with score
        stopTimer();
    }

    /**
     * Called when pressing Ok in the Score Menu
     * @param value passes true when done to know if to load next level or reset
     */
    @Override
    public void onDataPass2(boolean value) {
        if (isContinueLevels){
            if (currentLevel < LEVEL_NUMBER ){
                currentLevel++;
                initLevel(currentLevel);
            }else // initalize last level
                initLevel(currentLevel);
        }
        else{
            removeExistingFragment();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
