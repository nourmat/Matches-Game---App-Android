package com.example.matches;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements Game_First_level.OnFragmentInteractionListener,ScoreFragment.OnFragmentInteractionListener, OnDataPass {

    private Button btnBack, btnReset;
    private TextView textViewTime;
    private int timeMin, timeSec;
    private Thread threadTimer; //used to run timer in background
    private boolean timerOn = false; //used to control timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init(savedInstanceState);
        initGameFragment(savedInstanceState);
    }

    public void init(Bundle savedInstanceState){

        final Bundle tempSavedInstanceState = savedInstanceState; //used for passing

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
                initGameFragment(tempSavedInstanceState);
            }
        });
    }

    public void initGameFragment(Bundle savedInstanceState){
        stopTimer();
        removeExistingFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Intent intent = getIntent();
        int level = intent.getIntExtra(MainActivity.EXTRA_MESSAGE,0);

        switch (level) {
            case 1:
                if (findViewById(R.id.fragment_container) != null) {
//                    if (savedInstanceState != null) return;
                    Game_First_level firstLevel = new Game_First_level();
                    fragmentTransaction.replace(R.id.fragment_container, firstLevel);
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

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
