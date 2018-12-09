package com.example.dev.logobin.model;

public class M_Kala {

    String id ;
    String title;
    String cprice ;
    String code ;
    String details ;
    String image ;
    String active ;
    String factory_id ;
    String list_id ;
    String sublist_id ;

    public M_Kala(String id,
                  String title,
                  String cprice,
                  String code,
                  String details,
                  String image,
                  String active,
                  String factory_id,
                  String list_id,
                  String sublist_id) {
        this.id = id;
        this.title = title;
        this.cprice = cprice;
        this.code = code;
        this.details = details;
        this.image = image;
        this.active = active;
        this.factory_id = factory_id;
        this.list_id = list_id;
        this.sublist_id = sublist_id;
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

    public String getCprice() {
        return cprice;
    }

    public void setCprice(String cprice) {
        this.cprice = cprice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getFactory_id() {
        return factory_id;
    }

    public void setFactory_id(String factory_id) {
        this.factory_id = factory_id;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    public String getSublist_id() {
        return sublist_id;
    }

    public void setSublist_id(String sublist_id) {
        this.sublist_id = sublist_id;
    }
}
