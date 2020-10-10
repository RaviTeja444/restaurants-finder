package com.example.resturantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Filter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    }

    public void goBackToStartUp(View v){
        onBackPressed();
        //Intent startUpIntent=new Intent(this,Startup.class);
        //this.startActivity(startUpIntent);
    }

    public void goToRestaurantListonSearch(View v){
        Intent resListIntent=new Intent(this,FilterRestaurantList.class);
        this.startActivity(resListIntent);
    }
}