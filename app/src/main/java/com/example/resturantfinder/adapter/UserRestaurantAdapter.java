package com.example.resturantfinder.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.resturantfinder.R;
import com.example.resturantfinder.model.Restaurant;
import com.example.resturantfinder.utils.MainAcitivtyListner;

import java.util.ArrayList;

public class UserRestaurantAdapter  extends RecyclerView.Adapter<UserRestaurantAdapter.RecentsViewHolder> {
    private Context context;
    private ArrayList<Restaurant> restaurantList;
    private MainAcitivtyListner mainAcitivtyListner;

    public UserRestaurantAdapter(Context context, ArrayList<Restaurant> restaurantList,MainAcitivtyListner mainAcitivtyListner) {
        this.context = context;
        this.restaurantList = restaurantList;
        this.mainAcitivtyListner = mainAcitivtyListner;

    }
    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_user_list_item, viewGroup, false);
        return new UserRestaurantAdapter.RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {
        final Restaurant restaurant = restaurantList.get(position);
        Log.d("UserRestaurantAdapter", "restaurant : " + restaurant);
        Log.d("UserRestaurantAdapter", "getRestaurantName : " + restaurant.isIsfav());
        holder.item.setText(restaurant.getRestaurantName());

        if(restaurant.isIsfav()){
            holder.Button.setText("Remove From Fav");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.Button.setTextColor(context.getColor(R.color.red));
                holder.Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainAcitivtyListner.delFromFav(restaurant);
                    }
                });
            }
        }else {
            holder.Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainAcitivtyListner.click(restaurant);
                }
            });
        }
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

        android.widget.Button Button;
        TextView item;
        LinearLayout layout;

        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.restNameitemnew);
            Button=itemView.findViewById(R.id.button8d);
            layout=itemView.findViewById(R.id.restdetalis);

        }
    }
}
