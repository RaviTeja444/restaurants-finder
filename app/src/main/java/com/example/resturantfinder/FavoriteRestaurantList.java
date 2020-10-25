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
import android.widget.TextView;

public class FavoriteRestaurantList extends AppCompatActivity {

    private RemoveRestaurantAdapter restaurantAdapter = null;
    private RecyclerView recyclerView= null;
    private GestureDetectorCompat restaurantCheck = null;
    private String s="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_rest_list);
        Intent i=getIntent();
        s=i.getStringExtra("isFromAdmin").toString();

        restaurantAdapter= new RemoveRestaurantAdapter();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setAdapter(restaurantAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }



    public void goBackToHome(View v){
        //onBackPressed();
        Intent userHomePage=new Intent(this,user_home.class);
        userHomePage.putExtra("isFromAdmin",s);
        this.startActivity(userHomePage);
    }
}