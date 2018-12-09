package com.example.dev.logobin.model;

public class M_ZirDaste {

    int Uid;
    String id;
    String title;
    String list_id;

    public M_ZirDaste(String id, String title, String list_id) {
        this.id = id;
        this.title = title;
        this.list_id = list_id;
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

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }
}
