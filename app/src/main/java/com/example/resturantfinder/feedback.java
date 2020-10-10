package com.example.resturantfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    public void goToBack(View v){
        onBackPressed();
    }

    public void goToRestDetailPage(View v){
        onBackPressed();
    }
}