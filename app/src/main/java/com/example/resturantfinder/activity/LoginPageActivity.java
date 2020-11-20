package com.example.resturantfinder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.resturantfinder.R;
import com.example.resturantfinder.utils.MyConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPageActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        fAuth=FirebaseAuth.getInstance();

    }

    public void goToUserHome(View v){
        String uname=email.getText().toString().trim();
        String pswd=password.getText().toString().trim();
        if(TextUtils.isEmpty(uname)){
            email.setError("Email is required");
        }else if(TextUtils.isEmpty(pswd)){
            password.setError("Password is Required");
        }else {
            fAuth.signInWithEmailAndPassword(uname,pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent userHomePage=new Intent(getApplicationContext(), userHomeActivity.class);
                        userHomePage.putExtra(MyConstants.isFromAdmin,false);
                        startActivity(userHomePage);
                    }else {
                        Toast.makeText(getApplicationContext(), "Error Occurred"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    public void goToForgotPasswordPage(View v){
        Intent forgotPasswordPage=new Intent(getApplicationContext(), ForgotPassword.class);
        this.startActivity(forgotPasswordPage);
    }

    public void goToAdminLogin(View v){
        Intent adminLoginPage=new Intent(getApplicationContext(), AdminLoginActivity.class);
        this.startActivity(adminLoginPage);
    }

    public void goToRestaurantListPage(View v){
        Intent startupPage=new Intent(getApplicationContext(), MainActivity.class);
        this.startActivity(startupPage);
    }

    public void goToSIgnUpPage(View v){
        Intent startupPage=new Intent(getApplicationContext(), SignUpActivity.class);
        this.startActivity(startupPage);
    }

}