package com.example.resturantfinder;

public class RestaurantInfo {
    private String RestaurantEmail;
    private String RestaurantId;
    private String RestaurantLocation;
    private String RestaurantType;
    private String Website;
    private String RestaurantName;

    private void setRestaurantEmail(String restaurantEmail) {
        RestaurantEmail = restaurantEmail;
    }

    private void setRestaurantId(String restaurantId) {
        RestaurantId = restaurantId;
    }

    private void setRestaurantLocation(String restaurantLocation) {
        RestaurantLocation = restaurantLocation;
    }

    private void setRestaurantType(String restaurantType) {
        RestaurantType = restaurantType;
    }

    private void setWebsite(String website) {
        Website = website;
    }

    private void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }



    private RestaurantInfo() {
    }


    public RestaurantInfo(String restaurantEmail, String restaurantId, String restaurantLocation, String restaurantType, String website, String restaurantName) {
        RestaurantEmail = restaurantEmail;
        RestaurantId = restaurantId;
        RestaurantLocation = restaurantLocation;
        RestaurantType = restaurantType;
        Website = website;
        RestaurantName = restaurantName;
    }

    public String getRestaurantEmail() {
        return RestaurantEmail;
    }

    public String getRestaurantId() {
        return RestaurantId;
    }

    public String getRestaurantLocation() {
        return RestaurantLocation;
    }

    public String getRestaurantType() {
        return RestaurantType;
    }

    public String getWebsite() {
        return Website;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

}
