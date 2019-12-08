package com.example.matches.Objects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.matches.R;

public class Card extends AppCompatImageButton{

    private static final int backCard = R.drawable.backcard;
    public static final int DELAYTIME = 500;
    public static MediaPlayer mediaPlayer;
    private Handler handler;
    private Runnable runnable;
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
            mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(getContext(), sound);
        mediaPlayer.start();
    }

    public boolean isImageShown(){
        return (this.getVisibility() == VISIBLE);
    }

    @SuppressLint("StaticFieldLeak")
    public void delayHide(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                hideImage();
            }
        };
        handler.postDelayed(runnable,DELAYTIME);
    }
    @SuppressLint("StaticFieldLeak")
    public void delayDisappear(){
        Handler handler2 = new Handler();
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                disappearImage();
            }
        };
        handler2.postDelayed(runnable2,DELAYTIME - 200);
    }

    public void stopThread(){
        handler.removeCallbacks(runnable);
        this.hideImage();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) return false;

        Card otherCard = (Card)obj;
        return (otherCard.getImage() == this.image) && (otherCard.getSound() == this.sound);
    }
}
