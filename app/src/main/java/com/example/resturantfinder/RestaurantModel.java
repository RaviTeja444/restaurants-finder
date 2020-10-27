package com.example.resturantfinder;



import android.support.annotation.NonNull;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantModel {

    public ArrayList<Restaurant> restaurantList;
    //public ArrayList<Restaurant> favrestaurantList;
    private static RestaurantModel theModel=null;
    FirebaseDatabase database=null;
    DatabaseReference reference=null;

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
        public String RestaurantEmail;
        public String RestaurantId;
        public String RestaurantLocation;
        public String RestaurantType;
        public String Website;
        public String RestaurantName;

        public Restaurant(String restaurantEmail, String restaurantId, String restaurantLocation, String restaurantType, String website, String restaurantName) {
            RestaurantEmail = restaurantEmail;
            RestaurantId = restaurantId;
            RestaurantLocation = restaurantLocation;
            RestaurantType = restaurantType;
            Website = website;
            RestaurantName = restaurantName;
        }
/*public Restaurant(String name) {
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
        }*/


    }

    private void loadItems(){
        database = FirebaseDatabase.getInstance();
        reference=database.getReference().child("Restaurants");
        reference.addListenerForSingleValueEvent(
        new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // List<String> list = new ArrayList<>();
                /*for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Restaurant res = (Restaurant)ds.getValue();
                    restaurantList.add(res);
                }*/

                //Do what you need to do with your list
                GenericTypeIndicator<ArrayList<Restaurant>> genericTypeIndicator = new GenericTypeIndicator<ArrayList<Restaurant>>() {};
                ArrayList<Restaurant> rres=dataSnapshot.getValue(genericTypeIndicator);
                addRest(rres);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Error",databaseError.getMessage()); //Don't ignore errors!
            }
        });
        //reference.addListenerForSingleValueEvent(valueEventListener);

        /*Restaurant restaurantItem1=new Restaurant("McDonalds");
        Restaurant restaurantItem2=new Restaurant("Pound Sugar");
        Restaurant restaurantItem3=new Restaurant("Tea Cake");
        restaurantList.add(restaurantItem1);
        restaurantList.add(restaurantItem2);
        restaurantList.add(restaurantItem3);*/
    }
    private void addRest(ArrayList<Restaurant> array){
        for(Restaurant r:array){
            restaurantList.add(r);
        }


    }
}
