package com.example.dev.logobin.model;

public class Model_All {

    public Model_All() {
    }

    String type;
    int Image;
    String name;
    String rate;

    public Model_All(String type, int image, String name, String rate) {
        this.type = type;
        Image = image;
        this.name = name;
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
