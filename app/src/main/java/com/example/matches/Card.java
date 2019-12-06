package com.example.matches;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageButton;

public class Card extends AppCompatImageButton{

    private static final int backCard = R.drawable.backcard;
    static MediaPlayer mediaPlayer;
    public static final int DELAYTIME = 500;
    private int sound;
    private int image;

    public Card(Context context) {
        super(context);
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Card(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImage (int image) {
        this.image = image;
    }

    public void setSound(int sound) { this.sound = sound;
    }
    public int getSound() {
        return sound;
    }

    public int getImage() {
        return image;
    }

    public void showImage(){
        this.setBackgroundResource(image);
    }

    public void hideImage (){
        this.setBackgroundResource(backCard);
    }

    public void disappearImage(){
        this.setVisibility(INVISIBLE);
    }

    public void playSound(){
        if (mediaPlayer != null)
            mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(getContext(), sound);
        mediaPlayer.start();
    }

    public boolean isImageShown(){
        return (this.getVisibility() == VISIBLE);
    }

    public void delayHide(){
        @SuppressLint("StaticFieldLeak") AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(DELAYTIME);
                } catch (InterruptedException e) {
                    Log.d("tag", "Caught :" + e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                hideImage();
            }
        };

        asyncTask.execute();
    }

    public void delayHide(final Card secondCard){
        @SuppressLint("StaticFieldLeak") AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(DELAYTIME);
                } catch (InterruptedException e) {
                    Log.d("tag", "Caught :" + e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                hideImage();
                secondCard.hideImage();
            }
        };

        asyncTask.execute();
    }

    public void delayDisappear(final Card secondCard){
        @SuppressLint("StaticFieldLeak") AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(DELAYTIME);
                } catch (InterruptedException e) {
                    Log.d("tag", "Caught :" + e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                disappearImage();
                secondCard.disappearImage();
            }
        };

        asyncTask.execute();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) return false;

        Card otherCard = (Card)obj;
        return (otherCard.getImage() == this.image) && (otherCard.getSound() == this.sound);
    }
}
