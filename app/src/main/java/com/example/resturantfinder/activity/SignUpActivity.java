package com.example.resturantfinder.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.resturantfinder.R;
import com.example.resturantfinder.activity.LoginPageActivity;
import com.example.resturantfinder.utils.MyConstants;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private EditText uname;
    private EditText pswd;
    private EditText uPhoneET;
    private EditText uFirstNameET;
    private EditText uLastNameET;
    private FirebaseAuth fAuth;
    private String userRole = MyConstants.user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        uname = findViewById(R.id.editTextTextEmailAddress2);
        pswd = findViewById(R.id.editTextTextPassword);
        uPhoneET = findViewById(R.id.editTextPhone);
        uFirstNameET = findViewById(R.id.editTextTextPersonName);
        uLastNameET = findViewById(R.id.editTextTextPersonName2);
        fAuth = FirebaseAuth.getInstance();
        if(getIntent().getStringExtra(MyConstants.uRole)!=null){
            userRole   = MyConstants.Admin;
        }
    }

    public void goToLoginPage(View v) {
        String un = uname.getText().toString().trim();
        String psw = pswd.getText().toString().trim();
        if (TextUtils.isEmpty(un)) {
            uname.setError("Email is required");
        }else if (TextUtils.isEmpty(psw)) {
            pswd.setError("Password is Required");
        }else if (TextUtils.isEmpty(uPhoneET.getText().toString().trim())) {
            pswd.setError("Phone No. is Required");
        }else if (TextUtils.isEmpty(uFirstNameET.getText().toString().trim())) {
            pswd.setError("First name is Required");
        }else if (TextUtils.isEmpty(uLastNameET.getText().toString().trim())) {
            pswd.setError("Last Name is Required");
        }else {
            createAccount(un, psw);
        }
    }

    private void createAccount(String email, String password) {
        fAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            String ex = task.getException().toString();
                            Toast.makeText(getApplicationContext(), "Registration Failed" + ex,
                                    Toast.LENGTH_LONG).show();
                        } else {
                            FirebaseUser user = fAuth.getCurrentUser();
                            String uid = user.getUid();
                            if (uid != null)
                                saveOnDatabase(uid);
                        }
                    }
                });

    }

    private void saveOnDatabase(final String uId) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MyConstants.vacationtourapp, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MyConstants.uId, uId);
        editor.apply();

        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(MyConstants.USERS);
        HashMap<String, Object> userHashMap = new HashMap<>();
        userHashMap.put(MyConstants.uFirstName, uFirstNameET.getText().toString().trim());
        userHashMap.put(MyConstants.uLastName, uLastNameET.getText().toString().trim());
        userHashMap.put(MyConstants.uEmail, uname.getText().toString().trim());
        userHashMap.put(MyConstants.uPhone, uPhoneET.getText().toString().trim());
        userHashMap.put(MyConstants.uRole,userRole);
        rootRef.child(uId).updateChildren(userHashMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                             addList(uId);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("saveOnDatabase", "onFailure: ");
            }
        });
    }

    private void addList(final String uId){
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.child(MyConstants.FAVLIST_SIZE).child(uId).setValue("-1")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            fAuth.signOut();
                            Toast.makeText(getApplicationContext(), "Registration successful",
                                    Toast.LENGTH_SHORT).show();
                            Intent loginPageIntent = new Intent(getApplicationContext(), LoginPageActivity.class);
                            startActivity(loginPageIntent);
                        }
                    }
                });

    }


}