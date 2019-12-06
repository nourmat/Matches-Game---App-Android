package com.example.matches;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileObserver;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.BlockingDeque;

public class GameActivity extends AppCompatActivity implements Game_First_level.OnFragmentInteractionListener {

    private Button btnBack, btnExit;
    private TextView textViewScore, TextViewTime;

    static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();

        fragmentManager = getSupportFragmentManager();

        Intent intent = getIntent();
        int level = intent.getIntExtra(MainActivity.EXTRA_MESSAGE,0);

        switch (level) {
            case 1:
                if (findViewById(R.id.fragment_container) != null) {
                    if (savedInstanceState != null) return;
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Game_First_level firstLevel = new Game_First_level();
                    fragmentTransaction.add(R.id.fragment_container, firstLevel);
                    fragmentTransaction.commit();
                }
                break;
        }
    }

    public void init(){
        btnBack = findViewById(R.id.btnBack);
        btnExit = findViewById(R.id.btnReset);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
