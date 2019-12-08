package com.example.matches.Levels;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.matches.Objects.Card;
import com.example.matches.Objects.CardData;
import com.example.matches.Objects.OnDataPass;
import com.example.matches.R;

import java.util.ArrayList;
import java.util.Random;

public class Game_Fourth_level extends Fragment {

    private static final int NUMBER_OF_CARDS = 20; //No of cards in this level

    private Card card1,card2,card3,card4,card5,card6,card7,card8,card9,card10,
            card11,card12,card13,card14,card15,card16,card17,card18,card19,card20; //used to be binded with the frontend
    public Card selected1, selected2; //used to store last pressed Cards to check
    private int shownCardCount = 0; //used to track which state are you in
    private int countDisappearedCards = 0; //used to know if the game is finished

    private void inti(View view){
        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);
        card3 = view.findViewById(R.id.card3);
        card4 = view.findViewById(R.id.card4);
        card5 = view.findViewById(R.id.card5);
        card6 = view.findViewById(R.id.card6);
        card7 = view.findViewById(R.id.card7);
        card8 = view.findViewById(R.id.card8);
        card9 = view.findViewById(R.id.card9);
        card10 = view.findViewById(R.id.card10);
        card11 = view.findViewById(R.id.card11);
        card12 = view.findViewById(R.id.card12);
        card13 = view.findViewById(R.id.card13);
        card14 = view.findViewById(R.id.card14);
        card15 = view.findViewById(R.id.card15);
        card16 = view.findViewById(R.id.card16);
        card17 = view.findViewById(R.id.card17);
        card18 = view.findViewById(R.id.card18);
        card19 = view.findViewById(R.id.card19);
        card20 = view.findViewById(R.id.card20);

        View.OnClickListener listener = getListener();
        card1.setOnClickListener(listener);
        card2.setOnClickListener(listener);
        card3.setOnClickListener(listener);
        card4.setOnClickListener(listener);
        card5.setOnClickListener(listener);
        card6.setOnClickListener(listener);
        card7.setOnClickListener(listener);
        card8.setOnClickListener(listener);
        card10.setOnClickListener(listener);
        card11.setOnClickListener(listener);
        card12.setOnClickListener(listener);
        card13.setOnClickListener(listener);
        card14.setOnClickListener(listener);
        card15.setOnClickListener(listener);
        card16.setOnClickListener(listener);
        card17.setOnClickListener(listener);
        card18.setOnClickListener(listener);
        card19.setOnClickListener(listener);
        card20.setOnClickListener(listener);

    }

    private void initCards(){
        ArrayList<CardData> cardDataArrayList = new ArrayList<CardData>();
        ArrayList<Card> cardArrayList = new ArrayList<Card>();
        //Prepare the array lists to be used for looping
        addCards(cardArrayList);
        addData(cardDataArrayList);

        Random random = new Random();
        int currentCard = 0;
        while(cardDataArrayList.size() > 0) {
            int rand = random.nextInt(cardDataArrayList.size());

            cardArrayList.get(currentCard).setImage(cardDataArrayList.get(rand).getImage());
            cardArrayList.get(currentCard).setSound(cardDataArrayList.get(rand).getSound());

            cardArrayList.get(currentCard).hideImage();

            cardDataArrayList.remove(rand);
            currentCard++;
        }
    }

    //TODO add sounds
    private void addData(ArrayList<CardData> CardDataArrayList){
        CardDataArrayList.add(new CardData(R.drawable.hippo, R.raw.hippo));
        CardDataArrayList.add(new CardData(R.drawable.hippo, R.raw.hippo));
        CardDataArrayList.add(new CardData(R.drawable.lion, R.raw.lion));
        CardDataArrayList.add(new CardData(R.drawable.lion, R.raw.lion));
        CardDataArrayList.add(new CardData(R.drawable.monkey, R.raw.monkey));
        CardDataArrayList.add(new CardData(R.drawable.monkey, R.raw.monkey));
        CardDataArrayList.add(new CardData(R.drawable.wolf, R.raw.wolf));
        CardDataArrayList.add(new CardData(R.drawable.wolf, R.raw.wolf));
        CardDataArrayList.add(new CardData(R.drawable.hourse, R.raw.horse));
        CardDataArrayList.add(new CardData(R.drawable.hourse, R.raw.horse));
        CardDataArrayList.add(new CardData(R.drawable.chicken, R.raw.chicken));
        CardDataArrayList.add(new CardData(R.drawable.chicken, R.raw.chicken));
        CardDataArrayList.add(new CardData(R.drawable.dog, R.raw.dog));
        CardDataArrayList.add(new CardData(R.drawable.dog, R.raw.dog));
        CardDataArrayList.add(new CardData(R.drawable.crocodile, R.raw.crocodile));
        CardDataArrayList.add(new CardData(R.drawable.crocodile, R.raw.crocodile));
        CardDataArrayList.add(new CardData(R.drawable.cow, R.raw.cow));
        CardDataArrayList.add(new CardData(R.drawable.cow, R.raw.cow));
        CardDataArrayList.add(new CardData(R.drawable.pig, R.raw.pig));
        CardDataArrayList.add(new CardData(R.drawable.pig, R.raw.pig));
    }

    private void addCards(ArrayList<Card> cardArrayList){
        cardArrayList.add(card1);
        cardArrayList.add(card2);
        cardArrayList.add(card3);
        cardArrayList.add(card4);
        cardArrayList.add(card5);
        cardArrayList.add(card6);
        cardArrayList.add(card7);
        cardArrayList.add(card8);
        cardArrayList.add(card9);
        cardArrayList.add(card10);
        cardArrayList.add(card11);
        cardArrayList.add(card12);
        cardArrayList.add(card13);
        cardArrayList.add(card14);
        cardArrayList.add(card15);
        cardArrayList.add(card16);
        cardArrayList.add(card17);
        cardArrayList.add(card18);
        cardArrayList.add(card19);
        cardArrayList.add(card20);
    }

    private View.OnClickListener getListener(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card pressedCard = (Card)view;

                if (shownCardCount == 0) { //check first click
                    if (selected1 != null && selected1.isImageShown()) selected1.stopThread();
                    if (selected2 != null && selected2.isImageShown()) selected2.stopThread();

                    pressedCard.showImage();
                    pressedCard.playSound();
                    selected1 = pressedCard;

                    shownCardCount++;
                }
                else if (shownCardCount == 1){//check second click
                    if (selected1 == pressedCard) //check to see if same card is pressed
                        return;

                    pressedCard.showImage();
                    pressedCard.playSound();
                    selected2 = pressedCard;

                    shownCardCount = 0;
                    if(selected1.equals(selected2)) {//matches
                        selected1.delayDisappear();
                        selected2.delayDisappear();
                        selected1 = selected2 =  null;
                        countDisappearedCards += 2;
                        if (countDisappearedCards == NUMBER_OF_CARDS) finishedGame(); //if all cards are disappeared then game is finished
                    }
                    else {
                        selected1.delayHide();
                        selected2.delayHide();
                    }
                }
            }
        };
        return listener;
    }

    void finishedGame(){
        try {
            Thread.sleep(Card.DELAYTIME+100); //delay to wait until all images are disappeared
        }catch (Exception e){}
        OnDataPass dataPasser= (OnDataPass) getActivity();
        dataPasser.onDataPass(1);
        this.onDestroy();
    }

    /**---------------------------Fragment Settings-----------------------------------**/

    public Game_Fourth_level() {
        // Required empty public constructor
    }

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game__fourth_level, container, false);

        inti(view);
        initCards();

        // Inflate the layout for this fragment
        return view;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
