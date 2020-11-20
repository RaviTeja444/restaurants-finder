package com.example.resturantfinder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.resturantfinder.R;

public class MainActivity_ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLoginPage(View v){
        Intent loginPageIntent=new Intent(getApplicationContext(), LoginPageActivity.class);
        this.startActivity(loginPageIntent);
    }

    public void goToRegisterPage(View v){
        Intent registerPageIntent=new Intent(getApplicationContext(), SignUpActivity.class);
        this.startActivity(registerPageIntent);
    }
}