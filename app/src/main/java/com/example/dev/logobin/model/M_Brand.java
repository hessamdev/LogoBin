package com.example.dev.logobin.model;

public class M_Brand {

    String id;
    String title;
    String rate;
    String image;


    public M_Brand(String id, String title, String rate, String image) {
        this.id = id;
        this.title = title;
        this.rate = rate;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
