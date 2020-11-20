package com.example.resturantfinder.activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.resturantfinder.R;
import com.example.resturantfinder.model.Restaurant;
import com.example.resturantfinder.utils.MyConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddRestaurantActivity extends AppCompatActivity {

    private EditText foodStyleEt;
    private EditText timingEt;
    private EditText rName;
    private EditText contactNoEt;
    private EditText LocationEt;
    private EditText emailEt;
    private EditText orderlinkEt;
    private EditText zipCodeEt;
    private EditText cityEt;
    private EditText stateEt;
    private DatabaseReference databaseReference;
    private String RestaurantListValue,key,currentDate,currentTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rest);
        rName = findViewById(R.id.editTextTextPersonName91);
        foodStyleEt = findViewById(R.id.editTextTextPersonName9);
        timingEt = findViewById(R.id.editTextTextPersonName10);
        contactNoEt = findViewById(R.id.editTextPhone4);
        LocationEt = findViewById(R.id.editTextTextPersonName12);
        emailEt = findViewById(R.id.editTextTextEmailAddress4);
        orderlinkEt = findViewById(R.id.editTextTextPersonName14);
        zipCodeEt= findViewById(R.id.editTextTextPersonName121);
        cityEt= findViewById(R.id.City12);
        stateEt= findViewById(R.id.State1);

        databaseReference = FirebaseDatabase.getInstance().getReference();


    }
    public void addrest(View v){
        setClickable(false);
        Log.d("adfasdasd", "addrest: " );
        if(TextUtils.isEmpty(rName.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please Restaurant Name", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(foodStyleEt.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please Enter Timings", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(timingEt.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please Enter Timings", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(contactNoEt.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please Enter Contact Number", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(LocationEt.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please Enter Loaction", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(emailEt.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(orderlinkEt.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please Enter Order Link", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(cityEt.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please Enter City", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(stateEt.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please Enter State", Toast.LENGTH_SHORT).show();
        }else {
            getRestListSize();
        }
    }
    private void getRestListSize() {
        try {
            Log.d("adfasdasd", "getRestListSize: " );
            final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            rootRef.child(MyConstants.RestaurantList_SIZE).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String s = String.valueOf(snapshot.getValue());
                        int setTourNo = Integer.parseInt(s);
                        RestaurantListValue = String.valueOf(setTourNo + 1);
                        saveRestaurantInfo();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    setClickable(false);
                }//
            });
        } catch (Exception e) {
           // setClickable(false);
        }
    }

    private void saveRestaurantInfo() {
        try {
            Log.d("adfasdasd", "saveRestaurantInfo: " );
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat cuuremtDateFormat = new SimpleDateFormat("MMM dd, yyyy");
            currentDate = cuuremtDateFormat.format(calendar.getTime());
            SimpleDateFormat cuuremtDateFormat1 = new SimpleDateFormat("HH:mm:ss a");
            currentTime = cuuremtDateFormat1.format(calendar.getTime());
            key = (currentDate + currentTime).trim().replace(" ", "");
            Restaurant restaurant = new Restaurant();
            restaurant.setPosition(RestaurantListValue);
            restaurant.setRestaurantName(rName.getText().toString());
            restaurant.setRestaurantCity(cityEt.getText().toString());
            restaurant.setRestaurantState(stateEt.getText().toString());
            restaurant.setRestaurantId(key.trim());
            restaurant.setRestaurantfoodStyle(foodStyleEt.getText().toString());
            restaurant.setRestaurantTiming(timingEt.getText().toString());
            restaurant.setRestaurantcontactNo(contactNoEt.getText().toString());
            restaurant.setRestaurantemail(emailEt.getText().toString());
            restaurant.setRestaurantorderlink(orderlinkEt.getText().toString());
            restaurant.setZipCode(zipCodeEt.getText().toString());
            addRestaurant(restaurant);
        } catch (Exception e) {
            Log.d("adfasdasd", "Exception: " + e);

        }


    }

    private void addRestaurant(Restaurant restaurant) {
        try {

            Log.d("adfasdasd", "RestaurantListValue" + RestaurantListValue);
            databaseReference.child(MyConstants.Restaurant).child(RestaurantListValue.toString()).setValue(restaurant)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                databaseReference.child(MyConstants.RestaurantList_SIZE).setValue(RestaurantListValue)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                   setClickable(true);
                                                    Toast.makeText(getApplicationContext(),
                                                            "Restaurant is added....",
                                                            Toast.LENGTH_SHORT).show();
                                                    Intent intent= new Intent();
                                                    intent.putExtra(MyConstants.restaurantadded,true);
                                                    setResult(RESULT_OK,intent);
                                                    finish();
                                                }
                                            }
                                        });
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d("adfasdasd", "" + e);
        }
    }

    private void setClickable(boolean clickable) {

        ProgressBar progressBar = findViewById(R.id.pbbar3);
        LinearLayout linearLayout= findViewById(R.id.layout2);
        Button button= findViewById(R.id.button12);
        button.setClickable(clickable);
        if(clickable){
            linearLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }else {
            linearLayout.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }
    }




    public void goBack(View v){
        onBackPressed();
        finish();
    }
}