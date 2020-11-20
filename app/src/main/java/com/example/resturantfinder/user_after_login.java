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

public class user_after_login extends AppCompatActivity {

//    private RestaurantAdapter restaurantAdapter = null;
//    private RecyclerView recyclerView= null;
//    private GestureDetectorCompat restaurantCheck = null;
//    private String isFromAdmin="";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_after_login);
//        Intent i=getIntent();
//        String s=i.getStringExtra("isFromAdmin");
//        Intent ii=getIntent();
//        s=ii.getStringExtra("isFromAdmin").toString();
//
//        restaurantAdapter= new RestaurantAdapter();
//        recyclerView=findViewById(R.id.recyclerView);
//        recyclerView.setAdapter(restaurantAdapter);
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//
//
//
//        Button b=findViewById(R.id.userinfo);
//        if(s.equals("Y")){
//            b.setText("HELLO ADMIN");
//        }
//        else
//            b.setText("HELLO USER");
//    }
//
//
//
//    public void goToFilterPage(View v){
//        Intent filterIntent=new Intent(this,Filter.class);
//        this.startActivity(filterIntent);
//    }
}