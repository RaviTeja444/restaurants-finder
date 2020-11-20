package com.example.resturantfinder.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.resturantfinder.R;
import com.example.resturantfinder.model.User;
import com.example.resturantfinder.utils.MyConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AdminLoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("checkAdmin", "AdminLoginPage: ");
        setContentView(R.layout.activity_admin_login_page);
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        fAuth=FirebaseAuth.getInstance();
    }


    public void goToForgotPasswordPage(View v){
        Intent forgotPasswordPage=new Intent(getApplicationContext(), ForgotPassword.class);
        this.startActivity(forgotPasswordPage);
    }

    public void goToSIgnUpPage(View v){
        Intent intent=new Intent(getApplicationContext(), SignUpActivity.class);
        intent.putExtra(MyConstants.uRole,MyConstants.Admin);
        this.startActivity(intent);
    }

    public void goToAdminLoginfn(View v){
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
                        FirebaseUser user = fAuth.getCurrentUser();
                        if(user!=null){
                            String uid = user.getUid();
                            checkAdmin(uid);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error Occurred"
                                + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    private void checkAdmin(final String uid) {
        try{
            final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            rootRef.child(MyConstants.USERS).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(uid).exists()){
                        User user = snapshot.child(uid).getValue(User.class);
                        if (user != null && user.getUserRole() != null) {
                            Log.d("checkAdmin", "user.getUserRole(): " + user.getUserRole());
                            if (user.getUserRole().equalsIgnoreCase(MyConstants.Admin)) {
                                Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent managerestaurantPage = new Intent(getApplicationContext(), AdminRestListActivity.class);
                                startActivity(managerestaurantPage);
                            } else {
                                Toast.makeText(getApplicationContext(), "User is Not a Admin", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("fsfasfdsadaswd", "checkUser: DatabaseError:  "+error);
                }
            });
        }catch (Exception e){
            Log.d("fsfasfdsadaswd", "Exception: "+e);
        }
    }


}