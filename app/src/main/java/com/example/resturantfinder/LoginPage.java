package com.example.resturantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void goToUserHome(View v){
        Intent userHomePage=new Intent(this,user_home.class);
        userHomePage.putExtra("isFromAdmin","N");
        this.startActivity(userHomePage);
    }

    public void goToForgotPasswordPage(View v){
        Intent forgotPasswordPage=new Intent(this,ForgotPassword.class);
        this.startActivity(forgotPasswordPage);
    }

    public void goToAdminLogin(View v){
        Intent adminLoginPage=new Intent(this,AdminLoginPage.class);
        this.startActivity(adminLoginPage);
    }

    public void goToRestaurantListPage(View v){
        Intent startupPage=new Intent(this, MainActivity.class);
        this.startActivity(startupPage);
    }
}