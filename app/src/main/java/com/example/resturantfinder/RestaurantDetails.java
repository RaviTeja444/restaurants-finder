package com.example.resturantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RestaurantDetails extends AppCompatActivity {

    public String restName=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_list);
        Intent g=getIntent();
        restName=g.getStringExtra("restName");
        TextView restView=findViewById(R.id.textView10);
        restView.setText(restName);
    }
    public void goToFeedbackPage(View v){
        Intent filterIntent=new Intent(this,feedback.class);
        this.startActivity(filterIntent);
    }

    public void goBackToStartupPage(View v){
        Intent filterIntent=new Intent(this, MainActivity.class);
        this.startActivity(filterIntent);
    }
}