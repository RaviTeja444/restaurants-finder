package com.example.resturantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginPage extends AppCompatActivity {

    EditText email;
    EditText password;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        fAuth=FirebaseAuth.getInstance();
    }

    public void goToManageRestaurantPage(View v){
        String uname=email.getText().toString().trim();
        String pswd=password.getText().toString().trim();
        if(TextUtils.isEmpty(uname)){
            email.setError("Email is required");
            return;
        }

        if(TextUtils.isEmpty(pswd)){
            password.setError("Password is Required");
            return;
        }
        if(uname.equals("john@gmail.com") && pswd.equals("john@123")){
            Toast.makeText(AdminLoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            Intent managerestaurantPage=new Intent(this,admin_rest_list.class);
            this.startActivity(managerestaurantPage);
        }
        else{
            Toast.makeText(AdminLoginPage.this, "Error Occurred: please enter correct credentisls", Toast.LENGTH_SHORT).show();
        }

    }

    public void goToForgotPasswordPage(View v){
        Intent forgotPasswordPage=new Intent(this,ForgotPassword.class);
        this.startActivity(forgotPasswordPage);
    }

    public void goToSIgnUpPage(View v){
        Intent forgotPasswordPage=new Intent(this,SignUp.class);
        this.startActivity(forgotPasswordPage);
    }


}