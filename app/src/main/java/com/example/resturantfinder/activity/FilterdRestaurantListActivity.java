package com.example.resturantfinder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.resturantfinder.R;
import com.example.resturantfinder.adapter.FavouriteRestaurantAdapter;
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

public class FilterdRestaurantListActivity extends AppCompatActivity  implements MainAcitivtyListner {

    private ArrayList<Restaurant> restaurantList;
    private ArrayList<Restaurant> filterRestaurantList;
    private RecyclerView recyclerView= null;
    private String filterType;
    private String filterValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterd_restaurant_list);
        recyclerView=findViewById(R.id.recyclerViewFilterrdRestaurantList);
        Button backBtn = findViewById(R.id.vacfads);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getIntent().getStringExtra(MyConstants.filterType)!=null){
            filterType =getIntent().getStringExtra(MyConstants.filterType);
        }
        if (getIntent().getStringExtra(MyConstants.filterValue)!=null){
            filterValue =getIntent().getStringExtra(MyConstants.filterValue);
        }
        filterRestaurantList =new ArrayList<>();
        readData();
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
                    setFilter();
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

    private void setFilter() {
        if(filterType.equalsIgnoreCase(MyConstants.restaurantfoodStyle)){
                setListforFoodStyle();
            }else if(filterType.equalsIgnoreCase(MyConstants.restaurantState)){
            setListforState();
            }else if(filterType.equalsIgnoreCase(MyConstants.restaurantCity)){
            setListforCity();
            }else if(filterType.equalsIgnoreCase(MyConstants.restaurantzipCode)){
            setListforZipCode();
            }
        }

    private void setListforFoodStyle() {

        for(int i= 0;i<restaurantList.size();i++){
            Restaurant restaurant =   restaurantList.get(i);
            if(filterValue.equalsIgnoreCase(restaurant.getRestaurantfoodStyle())){
                filterRestaurantList.add(restaurant);
            }
        }
        setAdapterMethod(filterRestaurantList);
    }
    private void setListforState() {

        for(int i= 0;i<restaurantList.size();i++){
            Restaurant restaurant =   restaurantList.get(i);
            if(filterValue.equalsIgnoreCase(restaurant.getRestaurantState())){
                filterRestaurantList.add(restaurant);
            }
        }
        setAdapterMethod(filterRestaurantList);
    }
    private void setListforCity() {

        for(int i= 0;i<restaurantList.size();i++){
            Restaurant restaurant =   restaurantList.get(i);
            if(filterValue.equalsIgnoreCase(restaurant.getRestaurantCity())){
                filterRestaurantList.add(restaurant);
            }
        }
        setAdapterMethod(filterRestaurantList);
    }
    private void setListforZipCode() {
        for(int i= 0;i<restaurantList.size();i++){
            Restaurant restaurant =   restaurantList.get(i);
            if(filterValue.equalsIgnoreCase(restaurant.getZipCode())){
                filterRestaurantList.add(restaurant);
            }
        }
        setAdapterMethod(filterRestaurantList);
    }

    private void setAdapterMethod(ArrayList<Restaurant> restaurantList) {

        FavouriteRestaurantAdapter adapter = new FavouriteRestaurantAdapter(getApplicationContext(),restaurantList,FilterdRestaurantListActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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