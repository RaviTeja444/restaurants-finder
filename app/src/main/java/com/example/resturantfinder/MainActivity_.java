package com.example.resturantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity_ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLoginPage(View v){
        Intent loginPageIntent=new Intent(this,LoginPage.class);
        this.startActivity(loginPageIntent);
    }

    public void goToRegisterPage(View v){
        Intent registerPageIntent=new Intent(this,SignUp.class);
        this.startActivity(registerPageIntent);
    }
}