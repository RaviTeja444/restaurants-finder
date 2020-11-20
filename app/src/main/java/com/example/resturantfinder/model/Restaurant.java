package com.example.resturantfinder.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Restaurant implements Parcelable {
    private String restaurantName;
    private String  restaurantTiming;
    private String restaurantfoodStyle;
    private String restaurantcontactNo;
    private String restaurantLocation;
    private String restaurantemail;
    private String restaurantorderlink;
    private String restaurantId;
    private String zipCode;
    private String position;
    private String restaurantCity;

    protected Restaurant(Parcel in) {
        restaurantName = in.readString();
        restaurantTiming = in.readString();
        restaurantfoodStyle = in.readString();
        restaurantcontactNo = in.readString();
        restaurantLocation = in.readString();
        restaurantemail = in.readString();
        restaurantorderlink = in.readString();
        restaurantId = in.readString();
        zipCode = in.readString();
        position = in.readString();
        restaurantCity = in.readString();
        isfav = in.readByte() != 0;
        restaurantState = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(restaurantName);
        dest.writeString(restaurantTiming);
        dest.writeString(restaurantfoodStyle);
        dest.writeString(restaurantcontactNo);
        dest.writeString(restaurantLocation);
        dest.writeString(restaurantemail);
        dest.writeString(restaurantorderlink);
        dest.writeString(restaurantId);
        dest.writeString(zipCode);
        dest.writeString(position);
        dest.writeString(restaurantCity);
        dest.writeByte((byte) (isfav ? 1 : 0));
        dest.writeString(restaurantState);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public boolean isIsfav() {
        return isfav;
    }

    public void setIsfav(boolean isfav) {
        this.isfav = isfav;
    }

    private boolean isfav;

    public Restaurant(String restaurantName, String restaurantTiming,
                      String restaurantfoodStyle, String restaurantcontactNo,
                      String restaurantLocation, String restaurantemail, String restaurantorderlink,
                      String restaurantId, String zipCode,
                      String position, String restaurantCity,
                      boolean isfav, String restaurantState) {
        this.restaurantName = restaurantName;
        this.restaurantTiming = restaurantTiming;
        this.restaurantfoodStyle = restaurantfoodStyle;
        this.restaurantcontactNo = restaurantcontactNo;
        this.restaurantLocation = restaurantLocation;
        this.restaurantemail = restaurantemail;
        this.restaurantorderlink = restaurantorderlink;
        this.restaurantId = restaurantId;
        this.zipCode = zipCode;
        this.position = position;
        this.restaurantCity = restaurantCity;
        this.isfav = isfav;
        this.restaurantState = restaurantState;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    private String restaurantState;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantTiming() {
        return restaurantTiming;
    }

    public void setRestaurantTiming(String restaurantTiming) {
        this.restaurantTiming = restaurantTiming;
    }

    public String getRestaurantfoodStyle() {
        return restaurantfoodStyle;
    }

    public void setRestaurantfoodStyle(String restaurantfoodStyle) {
        this.restaurantfoodStyle = restaurantfoodStyle;
    }

    public String getRestaurantcontactNo() {
        return restaurantcontactNo;
    }

    public void setRestaurantcontactNo(String restaurantcontactNo) {
        this.restaurantcontactNo = restaurantcontactNo;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public String getRestaurantemail() {
        return restaurantemail;
    }

    public void setRestaurantemail(String restaurantemail) {
        this.restaurantemail = restaurantemail;
    }

    public String getRestaurantorderlink() {
        return restaurantorderlink;
    }

    public void setRestaurantorderlink(String restaurantorderlink) {
        this.restaurantorderlink = restaurantorderlink;
    }



    public Restaurant() {
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getRestaurantCity() {
        return restaurantCity;
    }

    public void setRestaurantCity(String restaurantCity) {
        this.restaurantCity = restaurantCity;
    }

    public String getRestaurantState() {
        return restaurantState;
    }

    public void setRestaurantState(String restaurantState) {
        this.restaurantState = restaurantState;
    }
}
