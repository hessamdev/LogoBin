package com.example.dev.logobin.model;

import android.nfc.Tag;

public class Model_ImageSlider {

    int image;
    String tag;

    public Model_ImageSlider(int image, String tag) {
        this.image = image;
        this.tag = tag;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
