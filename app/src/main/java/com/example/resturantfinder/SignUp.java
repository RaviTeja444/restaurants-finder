package com.example.resturantfinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SignUp extends AppCompatActivity {

    EditText uname;
    EditText pswd;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        uname=findViewById(R.id.editTextTextEmailAddress2);
        pswd=findViewById(R.id.editTextTextPassword);
        fAuth= FirebaseAuth.getInstance();

    }

    public void goToLoginPage(View v){
        String un=uname.getText().toString().trim();
        String psw=pswd.getText().toString().trim();
        if(TextUtils.isEmpty(un)){
            uname.setError("Email is required");
            return;
        }

        if(TextUtils.isEmpty(psw)){
            pswd.setError("Password is Required");
            return;
        }
        fAuth.createUserWithEmailAndPassword(un,psw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent loginPageIntent = new Intent(getApplicationContext(),LoginPage.class);
                    startActivity(loginPageIntent);
                }else {
                    Toast.makeText(SignUp.this, "Error Occurred"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}