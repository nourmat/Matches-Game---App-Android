package com.example.matches;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.matches.Objects.OnDataPass;

public class ScoreFragment extends Fragment {

    TextView textViewScore;
    Button btnOk;
    public static final String SCOREMESAGE = "scoremessage";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        int score = getArguments().getInt(SCOREMESAGE);

        textViewScore = view.findViewById(R.id.textViewScore);
        btnOk = view.findViewById(R.id.btnOk);

        textViewScore.setText("Your Score is " + score);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        return view;
    }

    private void finish(){
        OnDataPass dataPasser= (OnDataPass) getActivity();
        dataPasser.onDataPass2(true);
        this.onDestroy();
    }

    /**-------------------------------------------------**/

    public ScoreFragment() { }

    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
