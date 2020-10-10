package com.example.resturantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class user_home extends AppCompatActivity {

    public String s="N";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Intent i=getIntent();
         s=i.getStringExtra("isFromAdmin").toString();
        TextView userin=findViewById(R.id.welcomeuserid);
        if(s.equals("Y")){
            s="Y";
            userin.setText("WELCOME ADMIN");
        }
        else
            userin.setText("WELCOME USER");
    }

    public void goToRestaurantList(View v){
        Intent restaurantList =new Intent(this,user_after_login.class);
        restaurantList.putExtra("isFromAdmin",s);
        this.startActivity(restaurantList);
    }

    public void goToFavRestaurantList(View v){
        Intent favrestaurantList =new Intent(this, FavoriteRestaurantList.class);
        favrestaurantList.putExtra("isFromAdmin",s);
        this.startActivity(favrestaurantList);
    }
}