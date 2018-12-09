package com.example.dev.logobin.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Model_All implements Parcelable{

    public Model_All() {
    }

    String type;
    String id;
    String url_Image;
    String titel;
    int Image;
    String name;
    String rate;
    String gheymat;
    String time;

    public Model_All(String type, String id, String url_Image, String titel, String rate) {
        this.type = type;
        this.id = id;
        this.url_Image = url_Image;
        this.titel = titel;
        this.rate = rate;
    }

    public Model_All(String type, int image) {
        this.type = type;
        Image = image;
    }

    public Model_All(String type, String url_Image, String name) {
        this.type = type;
        this.url_Image = url_Image;
        this.name = name;
    }

//    public Model_All(String type, int image, String name) {
//        this.type = type;
//        Image = image;
//        this.name = name;
//    }

//    public Model_All(String type, int image, String rate, String gheymat, String time) {
//        this.type = type;
//        Image = image;
//        this.rate = rate;
//        this.gheymat = gheymat;
//        this.time = time;
//    }

    public Model_All(String type) {
        this.type = type;
    }

    public Model_All(String type, int image, String name, String rate) {
        this.type = type;
        Image = image;
        this.name = name;
        this.rate = rate;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl_Image() {
        return url_Image;
    }

    public void setUrl_Image(String url_Image) {
        this.url_Image = url_Image;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
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

    public String getGheymat() {
        return gheymat;
    }

    public void setGheymat(String gheymat) {
        this.gheymat = gheymat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    protected Model_All(Parcel in) {
        type = in.readString();
        Image = in.readInt();
        name = in.readString();
        rate = in.readString();
        gheymat = in.readString();
        time = in.readString();
    }

    public static final Creator<Model_All> CREATOR = new Creator<Model_All>() {
        @Override
        public Model_All createFromParcel(Parcel in) {
            return new Model_All(in);
        }

        @Override
        public Model_All[] newArray(int size) {
            return new Model_All[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeInt(Image);
        dest.writeString(name);
        dest.writeString(rate);
        dest.writeString(gheymat);
        dest.writeString(time);
    }
}

