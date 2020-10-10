package com.example.resturantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class user_after_login extends AppCompatActivity {

    private String isFromAdmin="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_after_login);
        Intent i=getIntent();
        String s=i.getStringExtra("isFromAdmin");
        Button b=findViewById(R.id.userinfo);
        if(s.equals("Y")){
            b.setText("HELLO ADMIN");
        }
        else
            b.setText("HELLO USER");
    }
    public void goToFilterPage(View v){
        Intent filterIntent=new Intent(this,Filter.class);
        this.startActivity(filterIntent);
    }
}