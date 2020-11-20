package com.example.resturantfinder.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.resturantfinder.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText emailIdtv;
    private Button button;
    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        emailIdtv= findViewById(R.id.forgotEmailAddress);
        button= findViewById(R.id.buttonsd2);
        fAuth= FirebaseAuth.getInstance();

        emailIdtv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void checkInputs() {

        if(TextUtils.isEmpty(emailIdtv.getText().toString())){
            button.setClickable(false);
            Toast.makeText(getApplicationContext(),"Please Enter Email First",Toast.LENGTH_SHORT).show();
        }else {
            button.setClickable(true);
        }
    }

    public void goToPasswordRestPage(View v){
        button.setClickable(false);

            fAuth.sendPasswordResetEmail(emailIdtv.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(),"A mail is sent On Your Registered Email!!",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    button.setClickable(true);
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                }
            });
    }
}