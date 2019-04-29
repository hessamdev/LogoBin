package com.example.dev.logobin.model;

import android.os.Parcel;
import android.os.Parcelable;

public class M_Home implements Parcelable {

    private String viewv,partId,partTitle,id,title,image,rate,state,type,price,lastseen,pack,unit,minimum,customers,cost;
    private Boolean Expend;


    public M_Home(String viewv, String partId, String partTitle, String id, String title, String image, String rate, String state, String price, String lastseen, String pack, String unit, String minimum, String customers, String cost, Boolean expend) {
        this.viewv = viewv;
        this.partId = partId;
        this.partTitle = partTitle;
        this.id = id;
        this.title = title;
        this.image = image;
        this.rate = rate;
        this.state = state;
        this.price = price;
        this.lastseen = lastseen;
        this.pack = pack;
        this.unit = unit;
        this.minimum = minimum;
        this.customers = customers;
        this.cost = cost;
        Expend = expend;
    }

    public M_Home(String viewv, String partId, String partTitle, String id, String title, String image, String rate, String state, String type) {
        this.viewv = viewv;
        this.partId = partId;
        this.partTitle = partTitle;
        this.id = id;
        this.title = title;
        this.image = image;
        this.rate = rate;
        this.state = state;
        this.type = type;
    }


    protected M_Home(Parcel in) {
        viewv = in.readString();
        partId = in.readString();
        partTitle = in.readString();
        id = in.readString();
        title = in.readString();
        image = in.readString();
        rate = in.readString();
        state = in.readString();
        type = in.readString();
        price = in.readString();
        lastseen = in.readString();
        pack = in.readString();
        unit = in.readString();
        minimum = in.readString();
        customers = in.readString();
        cost = in.readString();
    }

    public Boolean getExpend() {
        return Expend;
    }

    public void setExpend(Boolean expend) {
        Expend = expend;
    }

    public static final Creator<M_Home> CREATOR = new Creator<M_Home>() {
        @Override
        public M_Home createFromParcel(Parcel in) {
            return new M_Home(in);
        }

        @Override
        public M_Home[] newArray(int size) {
            return new M_Home[size];
        }
    };

    public String getLastseen() {
        return lastseen;
    }

    public void setLastseen(String lastseen) {
        this.lastseen = lastseen;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getCustomers() {
        return customers;
    }

    public void setCustomers(String customers) {
        this.customers = customers;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getViewv() {
        return viewv;
    }

    public void setViewv(String viewv) {
        this.viewv = viewv;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getPartTitle() {
        return partTitle;
    }

    public void setPartTitle(String partTitle) {
        this.partTitle = partTitle;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(viewv);
        dest.writeString(partId);
        dest.writeString(partTitle);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(rate);
        dest.writeString(state);
        dest.writeString(type);
        dest.writeString(price);
        dest.writeString(lastseen);
        dest.writeString(pack);
        dest.writeString(unit);
        dest.writeString(minimum);
        dest.writeString(customers);
        dest.writeString(cost);
    }
}
