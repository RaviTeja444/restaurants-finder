package com.example.resturantfinder;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class FilterRestaurantList extends AppCompatActivity {

    private RestaurantAdapter restaurantAdapter = null;
    private RecyclerView recyclerView= null;
    private GestureDetectorCompat restaurantCheck = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_restaurant_list);

        restaurantAdapter= new RestaurantAdapter();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setAdapter(restaurantAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    public void goBackTofilter(View v){
        onBackPressed();
        //Intent filterIntent=new Intent(this,Filter.class);
        //this.startActivity(filterIntent);
    }


    public void goBackToUserHome(View v){
        onBackPressed();
        //Intent UserIntent=new Intent(this,user_home.class);
        //this.startActivity(UserIntent);
    }

}