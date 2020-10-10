package com.example.resturantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class add_rest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rest);
    }

    public void addrest(View v){
        Intent addrest=new Intent(this,user_home.class);
        addrest.putExtra("isFromAdmin","Y");
        this.startActivity(addrest);
    }

    public void goBack(View v){
        onBackPressed();
       // Intent addrest=new Intent(this,user_home.class);
       // this.startActivity(addrest);
    }
}