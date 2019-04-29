package com.example.dev.logobin.model;

public class M_Sherkat {

    private String Id,Title,Image,state,rate;

    public M_Sherkat(String id, String title, String image, String state, String rate) {
        Id = id;
        Title = title;
        Image = image;
        this.state = state;
        this.rate = rate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
