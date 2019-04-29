package com.example.dev.logobin.model;

public class M_User {

    String Phone,Username,Ostan,Id_ostan,Shahr,Id_shahr,Mahale,Id_mahale,User_Id,Token;

    public M_User(String phone, String username, String ostan, String id_ostan, String shahr, String id_shahr, String mahale, String id_mahale, String user_Id, String token) {
        Phone = phone;
        Username = username;
        Ostan = ostan;
        Id_ostan = id_ostan;
        Shahr = shahr;
        Id_shahr = id_shahr;
        Mahale = mahale;
        Id_mahale = id_mahale;
        User_Id = user_Id;
        Token = token;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getOstan() {
        return Ostan;
    }

    public void setOstan(String ostan) {
        Ostan = ostan;
    }

    public String getId_ostan() {
        return Id_ostan;
    }

    public void setId_ostan(String id_ostan) {
        Id_ostan = id_ostan;
    }

    public String getShahr() {
        return Shahr;
    }

    public void setShahr(String shahr) {
        Shahr = shahr;
    }

    public String getId_shahr() {
        return Id_shahr;
    }

    public void setId_shahr(String id_shahr) {
        Id_shahr = id_shahr;
    }

    public String getMahale() {
        return Mahale;
    }

    public void setMahale(String mahale) {
        Mahale = mahale;
    }

    public String getId_mahale() {
        return Id_mahale;
    }

    public void setId_mahale(String id_mahale) {
        Id_mahale = id_mahale;
    }

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}

