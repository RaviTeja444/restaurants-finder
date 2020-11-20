package com.example.resturantfinder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.resturantfinder.R;
import com.example.resturantfinder.adapter.MainPageAdapter;
import com.example.resturantfinder.model.Restaurant;
import com.example.resturantfinder.utils.MainAcitivtyListner;
import com.example.resturantfinder.utils.MyConstants;
import com.example.resturantfinder.utils.ServerUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MainAcitivtyListner {


    //private ResAdapter resAdapter = null;
    private RecyclerView recyclerView ;
    private GestureDetectorCompat restaurantCheck = null;

    private ArrayList<Restaurant> restaurantList;
    //public static ArrayList<Restaurant.RestaurantInfo> resArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        recyclerView=findViewById(R.id.recyclerView);
        readData();

    }


    public void goToFilterPage(View v) {
        Intent filterIntent = new Intent(getApplicationContext(), ChooseFilterActivity.class);
       startActivity(filterIntent);
    }

    public void goToLoginPage(View v) {
        Intent loginIntent = new Intent(getApplicationContext(), LoginPageActivity.class);
       startActivity(loginIntent);
    }

    private void readData() {

        restaurantList =new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));

        JsonArrayRequest request = new JsonArrayRequest(ServerUtils.restaurantList, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("ResporeadData", "response : " + response);
                // Parsing json
                for (int i = (response.length()-1); i >0; i--) {
                    try {

                        JSONObject obj = response.getJSONObject(i);
                        Restaurant restaurant = new Restaurant();

                        restaurant.setPosition(obj.getString(MyConstants.restaurantPositon));
                        restaurant.setRestaurantName(obj.getString(MyConstants.restaurantName));
                        restaurant.setRestaurantCity(obj.getString(MyConstants.restaurantCity));
                        restaurant.setRestaurantState(obj.getString(MyConstants.restaurantState));
                        restaurant.setRestaurantId(obj.getString(MyConstants.restaurantId));
                        restaurant.setRestaurantfoodStyle(obj.getString(MyConstants.restaurantfoodStyle));
                        restaurant.setRestaurantTiming(obj.getString(MyConstants.restaurantTiming));
                        restaurant.setRestaurantcontactNo(obj.getString(MyConstants.restaurantcontactNo));
                        restaurant.setRestaurantemail(obj.getString(MyConstants.restaurantemail));
                        restaurant.setRestaurantorderlink(obj.getString(MyConstants.restaurantorderlink));
                        restaurant.setZipCode(obj.getString(MyConstants.restaurantzipCode));
                        if(!obj.getString(MyConstants.restaurantPositon).equalsIgnoreCase(MyConstants.restaurantDel)){
                            restaurantList.add(restaurant);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Response267", "JSONException : " + e);
                    }

                }
                MainPageAdapter adapter = new MainPageAdapter(getApplicationContext(),restaurantList,MainActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                        RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                Log.d("ResporeadData", "restaurantList : " + restaurantList.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response267", "error : " + error);
                // hidePDialog();

            }
        });
        queue.add(request);
        // Adding request to request queue


    }

    @Override
    public void click(Restaurant restaurant) {
        Intent loginIntent = new Intent(getApplicationContext(), LoginPageActivity.class);
        startActivity(loginIntent);
        Log.d("ResporeadData", "click : ");
        Log.d("ResporeadData", "restaurant : "+restaurant.getRestaurantName());

    }

    @Override
    public void delFromFav(Restaurant restaurant) {

    }

    @Override
    public void restaurantDetails(Restaurant restaurant) {
        Intent intent =new Intent(getApplicationContext(), RestaurantDetailsActivity.class);
        intent.putExtra(MyConstants.restaurantValue,restaurant);
        intent.putExtra(MyConstants.hideFeedback,true);
        startActivity(intent);

    }


}