package com.example.resturantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminLoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);
    }

    public void goToManageRestaurantPage(View v){
        Intent managerestaurantPage=new Intent(this,admin_rest_list.class);
        this.startActivity(managerestaurantPage);
    }

    public void goToForgotPasswordPage(View v){
        Intent forgotPasswordPage=new Intent(this,ForgotPassword.class);
        this.startActivity(forgotPasswordPage);
    }
}