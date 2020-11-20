package com.example.resturantfinder.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.resturantfinder.R;
import com.example.resturantfinder.model.FeedBack;
import com.example.resturantfinder.model.Restaurant;
import com.example.resturantfinder.utils.MyConstants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RestaurantDetailsActivity extends AppCompatActivity {
    private TextView restName,ratingtv,feedbackReviewtv,feedbackSuggestionv;
    private TextView restloc,resttype,restweb,restemail;
    private Button feebackBtn;
    private String key;
    private LinearLayout lastReviewLayout;
    private FeedBack feedBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_list);
        feebackBtn=findViewById(R.id.feebackBtn);
        restName=findViewById(R.id.restNamedet);
        restloc=findViewById(R.id.restlocdet);
        resttype=findViewById(R.id.resttypedet);
        restweb=findViewById(R.id.restweb);
        ratingtv=findViewById(R.id.Ratingtv);
        feedbackReviewtv=findViewById(R.id.feedbackReviewtv);
        feedbackSuggestionv=findViewById(R.id.feedbackSuggestionv);
        lastReviewLayout=findViewById(R.id.lastReviewLayout);
        restemail=findViewById(R.id.restemail);

        if(getIntent().getParcelableExtra(MyConstants.restaurantValue)!=null){
            Restaurant restaurant = getIntent().getParcelableExtra(MyConstants.restaurantValue);
            restName.setText(restaurant.getRestaurantName());
            restloc.setText(restaurant.getRestaurantCity());
            resttype.setText(restaurant.getRestaurantfoodStyle());
            key= restaurant.getRestaurantId();
            restweb.setText(restaurant.getRestaurantorderlink());
            restemail.setText(restaurant.getRestaurantemail());
            if(getIntent().getBooleanExtra(MyConstants.hideFeedback,false)){
                feebackBtn.setVisibility(View.INVISIBLE);
            }
            readFeadBack();
        }


    }

    private void readFeadBack() {
        try {
            Log.d("readFeadBack", "readFeadBack: " );
            final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            rootRef.child(MyConstants.FeedBack).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        feedBack = snapshot.getValue(FeedBack.class);
                        if(feedBack!=null){
                            lastReviewLayout.setVisibility(View.VISIBLE);
                            Log.d("readFeadBack", "feedBack: "+feedBack );
                            ratingtv.setText(feedBack.getFeedbackRating());
                            feedbackReviewtv.setText(feedBack.getFeedbackReview());
                            feedbackSuggestionv.setText(feedBack.getFeedbackSuggestion());
                        }
                    }else {
                        lastReviewLayout.setVisibility(View.GONE);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    lastReviewLayout.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            lastReviewLayout.setVisibility(View.GONE);
        }
    }

    public void goToFeedbackPage(View v){
        Intent filterIntent=new Intent(getApplicationContext(), feedbackActivity.class);
        filterIntent.putExtra(MyConstants.restaurantId,key);
        startActivityForResult(filterIntent,MyConstants.feedbackResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==MyConstants.feedbackResult){
            if(data!=null){
                if(data.getBooleanExtra(MyConstants.feedBackEntered,false)){
                    readFeadBack();
                }
            }
        }
    }

    public void goBackToStartupPage(View v){
        finish();
    }
}