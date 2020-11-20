package com.example.resturantfinder.activity;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.resturantfinder.adapter.FilterListNameAdapter;
import com.example.resturantfinder.model.Restaurant;
import com.example.resturantfinder.utils.Filtermethods;
import com.example.resturantfinder.utils.MyConstants;
import com.example.resturantfinder.utils.ServerUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilterRestaurantListActivity extends AppCompatActivity implements Filtermethods {

    private RecyclerView recyclerView= null;
    private ArrayList<Restaurant> restaurantList;
    private List<String> filterList ;
    private String filterTyp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_restaurant_list);
        recyclerView=findViewById(R.id.recyclerViewFilterRestaurantList);
        filterList =new ArrayList<>();
        readData();
    }


    private void setData(String filterType) {
        filterTyp=filterType;
        for(int i= 0;i<restaurantList.size();i++){
            String data="";
            Restaurant restaurant =   restaurantList.get(i);
            if(filterType.equalsIgnoreCase(MyConstants.restaurantfoodStyle)){
                data =restaurant.getRestaurantfoodStyle();
            }else if(filterType.equalsIgnoreCase(MyConstants.restaurantState)){
                data =restaurant.getRestaurantState();
            }else if(filterType.equalsIgnoreCase(MyConstants.restaurantCity)){
                data =restaurant.getRestaurantCity();
            }else if(filterType.equalsIgnoreCase(MyConstants.restaurantzipCode)){
                data =restaurant.getZipCode();
            }

                Log.d("checkFilter", "s : " + data);
            if (!filterList.contains(data)){
                    filterList.add(data);
            }

        }

        FilterListNameAdapter adapter = new FilterListNameAdapter(filterList,getApplicationContext(),FilterRestaurantListActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Log.d("checkFilter", "filterList : " + filterList);
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
                if (getIntent().getStringExtra(MyConstants.filterType)!=null){
                    String filterType =getIntent().getStringExtra(MyConstants.filterType);
                    setData(filterType);
                }
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



    public void goBackTofilter(View v){
        onBackPressed();
    }

    @Override
    public void getDeatails(Restaurant restaurant) {

    }

    @Override
    public void setFilter(String filterValue) {
        Intent resListIntent=new Intent(getApplicationContext(), FilterdRestaurantListActivity.class);
        resListIntent.putExtra(MyConstants.filterType,filterTyp);
        resListIntent.putExtra(MyConstants.filterValue,filterValue);
        startActivity(resListIntent);

    }
}