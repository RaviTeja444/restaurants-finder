package com.example.resturantfinder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.resturantfinder.R;
import com.example.resturantfinder.model.Restaurant;
import com.example.resturantfinder.utils.MainAcitivtyListner;

import java.util.ArrayList;

public class AdminRestaurantListAdapter extends RecyclerView.Adapter<AdminRestaurantListAdapter.RecentsViewHolder> {

    private Context context;
    private ArrayList<Restaurant> restaurantList;
    private MainAcitivtyListner mainAcitivtyListner;

    public AdminRestaurantListAdapter(Context context, ArrayList<Restaurant> restaurantList,MainAcitivtyListner mainAcitivtyListner) {
        this.context = context;
        this.restaurantList = restaurantList;
        this.mainAcitivtyListner = mainAcitivtyListner;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_admin_rest_list, viewGroup, false);
        return new AdminRestaurantListAdapter.RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {
        final Restaurant restaurant = restaurantList.get(position);
        Log.d("ResporeadData", "getRestaurantName : " + restaurant.getRestaurantName());
        holder.item.setText(restaurant.getRestaurantName());
        holder.Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainAcitivtyListner.click(restaurant);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainAcitivtyListner.restaurantDetails(restaurant);
            }
        });

    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static final class RecentsViewHolder extends RecyclerView.ViewHolder {
        Button Button;
        TextView item;
        LinearLayout layout;
        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.restName45);
            Button=itemView.findViewById(R.id.deleterestbtn);
            layout=itemView.findViewById(R.id.restdetailsadmin);

        }
    }
}
