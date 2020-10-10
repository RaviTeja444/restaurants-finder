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
import android.widget.Button;

public class user_after_login extends AppCompatActivity {

    private RestaurantAdapter restaurantAdapter = null;
    private RecyclerView recyclerView= null;
    private GestureDetectorCompat restaurantCheck = null;
    private String isFromAdmin="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_after_login);
        Intent i=getIntent();
        String s=i.getStringExtra("isFromAdmin");
        Intent ii=getIntent();
        s=ii.getStringExtra("isFromAdmin").toString();

        restaurantAdapter= new RestaurantAdapter();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setAdapter(restaurantAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Initially setting the total price to 0.0 when activity opens first time
        //totalPrice.setText("0.0");
        restaurantCheck=new GestureDetectorCompat(this, new user_after_login.RecyclerViewOnGestureListener());
        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return restaurantCheck.onTouchEvent(e);
            }
        });


        Button b=findViewById(R.id.userinfo);
        if(s.equals("Y")){
            b.setText("HELLO ADMIN");
        }
        else
            b.setText("HELLO USER");
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
}