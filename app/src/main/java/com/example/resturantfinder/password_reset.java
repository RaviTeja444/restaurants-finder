package com.example.resturantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.resturantfinder.activity.LoginPageActivity;

public class password_reset extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
    }

    public void goToLoginPage(View v){
        Intent loginPage=new Intent(this, LoginPageActivity.class);
        this.startActivity(loginPage);
    }
}