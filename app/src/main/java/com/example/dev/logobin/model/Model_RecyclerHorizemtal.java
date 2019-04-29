package com.example.dev.logobin.model;

import java.util.ArrayList;

public class Model_RecyclerHorizemtal {

    private String type;
    private String Titel;
    private ArrayList<M_Home> list_horizental;

    public Model_RecyclerHorizemtal(String type, String titel, ArrayList<M_Home> list_horizental) {
        this.type = type;
        Titel = titel;
        this.list_horizental = list_horizental;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitel() {
        return Titel;
    }

    public void setTitel(String titel) {
        Titel = titel;
    }

    public ArrayList<M_Home> getList_horizental() {
        return list_horizental;
    }

    public void setList_horizental(ArrayList<M_Home> list_horizental) {
        this.list_horizental = list_horizental;
    }
}