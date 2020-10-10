package com.example.resturantfinder;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RestaurantAdapter restaurantAdapter = null;
    private RecyclerView recyclerView= null;
    private GestureDetectorCompat restaurantCheck = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        restaurantAdapter= new RestaurantAdapter();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setAdapter(restaurantAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Initially setting the total price to 0.0 when activity opens first time
        //totalPrice.setText("0.0");
        restaurantCheck=new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());
        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return restaurantCheck.onTouchEvent(e);
            }
        });
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                if (viewHolder instanceof RestaurantAdapter.RestaurantViewHolder) {
                    int position = viewHolder.getAdapterPosition();
                    RestaurantModel myModel =RestaurantModel.getSingleton();


                    for(int i=0;i< restaurantAdapter.restaurantModel.restaurantList.size();i++){
                        //restaurantAdapter.restaurantModel.addItem(i);
                    }
                    ///finalSum=restaurantAdapter.groceryModel.getTotalPrice();


                    //totalPrice.setText(finalSum+"");
                    restaurantAdapter.notifyItemRemoved(position);
                    return true;
                }
            }
            return false;
        }
    }

    public void goToFilterPage(View v){
        Intent filterIntent=new Intent(this,Filter.class);
        this.startActivity(filterIntent);
    }

    public void goToLoginPage(View v){
        Intent loginIntent=new Intent(this,LoginPage.class);
        this.startActivity(loginIntent);
    }
}