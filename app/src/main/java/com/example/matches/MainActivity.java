package com.example.matches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "LEVEL";
    private TextView textViewFirstOption, textViewSecondOption, textViewThirdOption, textViewFourthOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewFirstOption = findViewById(R.id.first_option);
        textViewSecondOption = findViewById(R.id.second_option);
        textViewThirdOption = findViewById(R.id.third_option);
        textViewFourthOption = findViewById(R.id.fourth_option);

        View.OnClickListener textViewListener = makeListener();

        textViewFirstOption.setOnClickListener(textViewListener);
        textViewSecondOption.setOnClickListener(textViewListener);
        textViewThirdOption.setOnClickListener(textViewListener);
        textViewFourthOption.setOnClickListener(textViewListener);
    }

    public View.OnClickListener makeListener(){
        View.OnClickListener textViewListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.first_option:
                            Intent intent = new Intent(getApplicationContext(),GameActivity.class);
                            intent.putExtra(EXTRA_MESSAGE, 1);
                            startActivity(intent);
                        break;
                    case R.id.second_option:
                        break;
                    case R.id.third_option:
                        Intent intent3 = new Intent(getApplicationContext(),CreditActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.fourth_option:
                        finish();
                        break;
                }
            }
        };
        return textViewListener;
    }
}
