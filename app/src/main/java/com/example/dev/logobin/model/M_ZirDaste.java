package com.example.dev.logobin.model;

import java.util.ArrayList;
import java.util.List;

public class M_ZirDaste {

    private String id;
    private String title;
    private String list_id;

    private String sublist;
    private Boolean click;
    private ArrayList<M_ZirDaste> list;

    public M_ZirDaste(String sublist, Boolean click, ArrayList<M_ZirDaste> list) {
        this.sublist = sublist;
        this.click = click;
        this.list = list;
    }

    public M_ZirDaste(String id, String title, String list_id) {
        this.id = id;
        this.title = title;
        this.list_id = list_id;
    }

    public Boolean getClick() {
        return click;
    }

    public void setClick(Boolean click) {
        this.click = click;
    }

    public String getSublist() {
        return sublist;
    }

    public void setSublist(String sublist) {
        this.sublist = sublist;
    }

    public ArrayList<M_ZirDaste> getList() {
        return list;
    }

    public void setList(ArrayList<M_ZirDaste> list) {
        this.list = list;
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
