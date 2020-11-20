package com.example.resturantfinder.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.resturantfinder.R;
import com.example.resturantfinder.model.FeedBack;
import com.example.resturantfinder.model.Restaurant;
import com.example.resturantfinder.utils.MyConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedbackActivity extends AppCompatActivity {

    private EditText feedbackRating,ratingReview,ratingSuggestion;
    private String key;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedbackRating= findViewById(R.id.feedbackRating);
        ratingReview= findViewById(R.id.ratingReview);
        ratingSuggestion= findViewById(R.id.ratingReview2);
        if(getIntent().getStringExtra(MyConstants.restaurantId)!=null){
            key=getIntent().getStringExtra(MyConstants.restaurantId);
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void goToBack(View v){

        onBackPressed();
        finish();
    }

    public void goToRestDetailPage(View v){

        FeedBack feedBack = new FeedBack();
        feedBack.setFeedbackRating(feedbackRating.getText().toString());
        feedBack.setFeedbackReview(ratingReview.getText().toString());
        feedBack.setFeedbackSuggestion(ratingSuggestion.getText().toString());
        saveFeedback(feedBack);
    }

    public void saveFeedback(FeedBack feedBack){
        try {

            databaseReference.child(MyConstants.FeedBack).child(key).setValue(feedBack)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),
                                        "FeedBack is added....",
                                        Toast.LENGTH_SHORT).show();

                                Intent intent= new Intent();
                                intent.putExtra(MyConstants.feedBackEntered,true);
                                setResult(RESULT_OK,intent);
                                finish();
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d("adfasdasd", "" + e);
        }
    }


}