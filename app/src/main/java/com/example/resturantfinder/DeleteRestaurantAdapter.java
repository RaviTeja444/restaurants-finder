package com.example.resturantfinder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DeleteRestaurantAdapter extends RecyclerView.Adapter<DeleteRestaurantAdapter.RestaurantViewHolder> {
    RestaurantModel  restaurantModel ;
    public DeleteRestaurantAdapter() {
        super();
        restaurantModel = restaurantModel.getSingleton();
    }
    @NonNull
    @Override
    public DeleteRestaurantAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_admin_rest_list,viewGroup,false);
        DeleteRestaurantAdapter.RestaurantViewHolder RestaurantViewHolder = new DeleteRestaurantAdapter.RestaurantViewHolder(linearLayout,viewGroup);
        return RestaurantViewHolder;
    }

}
