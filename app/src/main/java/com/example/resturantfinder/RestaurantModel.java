package com.example.resturantfinder;

import java.util.ArrayList;

public class RestaurantModel {

    public ArrayList<Restaurant> restaurantList;
    //public ArrayList<Restaurant> favrestaurantList;
    private static RestaurantModel theModel=null;
    private RestaurantModel(){
        restaurantList=new ArrayList<Restaurant>();
        loadItems();
        //favloadItems();
    }
    public static RestaurantModel getSingleton() {
        if (theModel == null)
            theModel = new RestaurantModel();
        return theModel;
    }
    public static class Restaurant {
        public String Name;
        public String content;

        public Restaurant(String name) {
            Name = name;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }


    }

    private void loadItems(){
        Restaurant restaurantItem1=new Restaurant("McDonalds");
        Restaurant restaurantItem2=new Restaurant("Pound Sugar");
        Restaurant restaurantItem3=new Restaurant("Tea Cake");
        restaurantList.add(restaurantItem1);
        restaurantList.add(restaurantItem2);
        restaurantList.add(restaurantItem3);
    }
    /*private void favloadItems(){
        Restaurant restaurantItem1=new Restaurant("KFC");
        Restaurant restaurantItem2=new Restaurant("Pound Sugar");
        Restaurant restaurantItem3=new Restaurant("SubWay");
        favrestaurantList.add(restaurantItem1);
        favrestaurantList.add(restaurantItem2);
        favrestaurantList.add(restaurantItem3);
    }*/
}
