package com.example.resturantfinder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.resturantfinder.R;
import com.example.resturantfinder.utils.MyConstants;
import com.google.firebase.auth.FirebaseAuth;

public class userHomeActivity extends AppCompatActivity {

    private boolean addminView =false;
    private Button userLogoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        userLogoutBtn= findViewById(R.id.userLogout);
        if(getIntent().getBooleanExtra(MyConstants.isFromAdmin,false)){
            addminView=getIntent().getBooleanExtra(MyConstants.isFromAdmin,false);
        }
        TextView userin=findViewById(R.id.welcomeuserid);
        if(addminView){
            userin.setText("WELCOME ADMIN");
           // userLogoutBtn.setVisibility(View.INVISIBLE);
        }
        else{
            userin.setText("WELCOME USER");
            userLogoutBtn.setVisibility(View.VISIBLE);
        }

        userLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth fAuth= FirebaseAuth.getInstance();
                fAuth.signOut();
                Intent loginIntent = new Intent(getApplicationContext(), LoginPageActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

    }




    public void goToRestaurantList(View v){
        Intent restaurantList =new Intent(getApplicationContext(), userRestoListActivity.class);
        restaurantList.putExtra(MyConstants.isFromAdmin,addminView);
        startActivity(restaurantList);
    }

    public void goToFavRestaurantList(View v){
        Intent favrestaurantList =new Intent(getApplicationContext(), FavoriteRestaurantListActivity.class);
        favrestaurantList.putExtra(MyConstants.isFromAdmin,addminView);
        startActivity(favrestaurantList);
    }
}