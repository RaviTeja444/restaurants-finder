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
import android.widget.Button;

public class admin_rest_list extends AppCompatActivity {


//    private DeleteRestaurantAdapter restaurantAdapter = null;
//    private RecyclerView recyclerView= null;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_admin_rest_list);
//
//        restaurantAdapter= new DeleteRestaurantAdapter();
//        recyclerView=findViewById(R.id.recyclerView);
//        recyclerView.setAdapter(restaurantAdapter);
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//    }



    public void goToAdminHomePage(View v){
        Intent adminHomePage=new Intent(this,user_home.class);
        adminHomePage.putExtra("isFromAdmin","Y");
        this.startActivity(adminHomePage);
    }

    public void goToAddRestPage(View v){
        Intent addrestPage=new Intent(this,add_rest.class);
        this.startActivity(addrestPage);
    }
}