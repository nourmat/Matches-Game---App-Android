package com.example.matches.Objects;

public class CardData {

    private int sound;
    private int image;

    public CardData (int image,int sound){
        this.sound = sound;
        this.image = image;
    }

    public int getSound() {
        return sound;
    }

    public int getImage() {
        return image;
    }
}
