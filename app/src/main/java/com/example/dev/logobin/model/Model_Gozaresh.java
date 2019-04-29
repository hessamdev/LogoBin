package com.example.dev.logobin.model;

public class Model_Gozaresh {

    private String mimage;
    private String mtime;
    private String mData;
    private String title;
    private String rate;
    private String id_sherkat;

    public Model_Gozaresh(String mimage, String mtime, String mData, String title, String rate, String id_sherkat) {
        this.mimage = mimage;
        this.mtime = mtime;
        this.mData = mData;
        this.title = title;
        this.rate = rate;
        this.id_sherkat = id_sherkat;
    }

    public String getId_sherkat() {
        return id_sherkat;
    }

    public void setId_sherkat(String id_sherkat) {
        this.id_sherkat = id_sherkat;
    }

    public String getMimage() {
        return mimage;
    }

    public void setMimage(String mimage) {
        this.mimage = mimage;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getmData() {
        return mData;
    }

    public void setmData(String mData) {
        this.mData = mData;
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
}
