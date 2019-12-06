package com.example.matches;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScoreFragment extends Fragment {

    TextView textViewScore;
    public static final String SCOREMESAGE = "scoremessage";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        int score = getArguments().getInt(SCOREMESAGE);

        textViewScore = view.findViewById(R.id.textViewScore);
        textViewScore.setText("Your Score is " + score);

        return view;
    }

    /**-------------------------------------------------**/

    public ScoreFragment() { }

    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
