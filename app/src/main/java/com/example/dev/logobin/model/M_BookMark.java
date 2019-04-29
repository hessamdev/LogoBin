package com.example.dev.logobin.model;

public class M_BookMark {
    private String Id,Image,Titel,Rate;

    public M_BookMark(String id, String image, String titel, String rate) {
        Id = id;
        Image = image;
        Titel = titel;
        Rate = rate;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitel() {
        return Titel;
    }

    public void setTitel(String titel) {
        Titel = titel;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }
}
