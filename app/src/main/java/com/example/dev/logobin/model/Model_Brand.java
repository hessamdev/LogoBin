package com.example.dev.logobin.model;

public class Model_Brand {
    String type;
    String id;
    String title;
    String url_image;
    String rate;

    public Model_Brand(String type, String id, String title, String url_image, String rate) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.url_image = url_image;
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
