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
import com.example.resturantfinder.adapter.MainPageAdapter;
import com.example.resturantfinder.model.Restaurant;
import com.example.resturantfinder.utils.MyConstants;
import com.example.resturantfinder.utils.ServerUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class ChooseFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Button foodStyleFiterBtn = findViewById(R.id.foodStyleFiterBtn);
        Button cityFiterBtn = findViewById(R.id.cityFiterBtn);
        Button  stateFiterBtn = findViewById(R.id.stateFiterBtn);
        Button  zipCodeFiterBtn = findViewById(R.id.zipCodeFiterBtn);

        foodStyleFiterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRestaurantListonSearch(MyConstants.restaurantfoodStyle);
            }
        });
        cityFiterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRestaurantListonSearch(MyConstants.restaurantCity);
            }
        });
        stateFiterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRestaurantListonSearch(MyConstants.restaurantState);
            }
        });
        zipCodeFiterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRestaurantListonSearch(MyConstants.restaurantzipCode);
            }
        });
    }

    
    public void goBackToStartUp(View v){
        onBackPressed();
    }
    public void goToRestaurantListonSearch(String filterType){
        Intent resListIntent=new Intent(getApplicationContext(), FilterRestaurantListActivity.class);
        resListIntent.putExtra(MyConstants.filterType,filterType);
        startActivity(resListIntent);
    }

    public void goToRestaurantListonSearch(View v){
        Intent resListIntent=new Intent(getApplicationContext(), FilterRestaurantListActivity.class);
        startActivity(resListIntent);
    }

}