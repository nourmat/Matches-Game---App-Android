package com.example.matches;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements Game_First_level.OnFragmentInteractionListener,ScoreFragment.OnFragmentInteractionListener, OnDataPass {

    private Button btnBack, btnReset;
    private TextView textViewScore, textViewTime;
    static FragmentManager fragmentManager;

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
        textViewScore = findViewById(R.id.textViewScore);
        textViewTime = findViewById(R.id.textViewTime);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        removeExistingFragment();

        //prepare fragment objects
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Intent intent = getIntent();
        int level = intent.getIntExtra(MainActivity.EXTRA_MESSAGE,0);

        switch (level) {
            case 1:
                if (findViewById(R.id.fragment_container) != null) {
                    if (savedInstanceState != null) return;
                    Game_First_level firstLevel = new Game_First_level();
                    fragmentTransaction.add(R.id.fragment_container, firstLevel);
                    fragmentTransaction.commit();
                }
                break;
        }
    }

    public void removeExistingFragment(){
        if (Card.mediaPlayer != null)//make sure no sound is playing
            Card.mediaPlayer.stop();

        //prepare fragment objects
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (!fragmentTransaction.isEmpty()) //remove existing fragment if there found
            fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.fragment_container)).commit();
    }

    public void startTimer(){

    }
    public void stopTimer(){

    }

    public void showScore(){
        int score = 56789;
        Bundle bundle = new Bundle();
        bundle.putInt(ScoreFragment.SCOREMESAGE,score);
        ScoreFragment scoreFragment = new ScoreFragment();
        scoreFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
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

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
