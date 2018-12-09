package com.example.dev.logobin.model;

public class Model_User {

    int id;
    String Phone,name,family,ostan,shahr,mahale,adres;

    public Model_User() {
    }

    public Model_User(int id, String phone, String name, String family, String ostan, String shahr, String mahale, String adres) {
        this.id = id;
        Phone = phone;
        this.name = name;
        this.family = family;
        this.ostan = ostan;
        this.shahr = shahr;
        this.mahale = mahale;
        this.adres = adres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getOstan() {
        return ostan;
    }

    public void setOstan(String ostan) {
        this.ostan = ostan;
    }

    public String getShahr() {
        return shahr;
    }

    public void setShahr(String shahr) {
        this.shahr = shahr;
    }

    public String getMahale() {
        return mahale;
    }

    public void setMahale(String mahale) {
        this.mahale = mahale;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
