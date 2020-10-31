package com.example.resturantfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    EditText email;
    EditText password;
    FirebaseAuth fAuth;
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
            return;
        }

        if(TextUtils.isEmpty(pswd)){
            password.setError("Password is Required");
            return;
        }
        fAuth.signInWithEmailAndPassword(uname,pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent userHomePage=new Intent(getApplicationContext(),user_home.class);
                    userHomePage.putExtra("isFromAdmin","N");
                    startActivity(userHomePage);
                }else {
                    Toast.makeText(LoginPage.this, "Error Occurred"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}