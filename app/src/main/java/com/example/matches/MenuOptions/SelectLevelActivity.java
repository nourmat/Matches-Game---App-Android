package com.example.matches.MenuOptions;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.matches.MainActivity;
import com.example.matches.R;

public class SelectLevelActivity extends AppCompatActivity {

    private TextView textViewFirstOption, textViewSecondOption, textViewThirdOption, textViewFourthOption;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        textViewFirstOption = findViewById(R.id.first_option);
        textViewSecondOption = findViewById(R.id.second_option);
        textViewThirdOption = findViewById(R.id.third_option);
        textViewFourthOption = findViewById(R.id.fourth_option);

        View.OnClickListener textViewListener = makeListener();

        textViewFirstOption.setOnClickListener(textViewListener);
        textViewSecondOption.setOnClickListener(textViewListener);
        textViewThirdOption.setOnClickListener(textViewListener);
        textViewFourthOption.setOnClickListener(textViewListener);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public View.OnClickListener makeListener(){
        View.OnClickListener textViewListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                switch (view.getId()) {
                    case R.id.first_option:
                        intent.putExtra(MainActivity.LEVEL_MESSAGE, 1);
                        break;
                    case R.id.second_option:
                        intent.putExtra(MainActivity.LEVEL_MESSAGE, 2);
                        break;
                    case R.id.third_option:
                        intent.putExtra(MainActivity.LEVEL_MESSAGE, 3);
                        break;
                    case R.id.fourth_option:
                        intent.putExtra(MainActivity.LEVEL_MESSAGE, 4);
                        break;
                }
                intent.putExtra(MainActivity.ISCONTINUE_MESSAGE, false);
                startActivity(intent);
            }
        };
        return textViewListener;
    }

}
