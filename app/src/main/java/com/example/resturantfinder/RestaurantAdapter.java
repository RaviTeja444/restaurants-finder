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

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    RestaurantModel  restaurantModel ;
    public RestaurantAdapter() {
        super();
        restaurantModel = restaurantModel.getSingleton();
    }
    @NonNull
    @Override
    public RestaurantAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_startup_recycler,viewGroup,false);
        RestaurantViewHolder RestaurantViewHolder = new RestaurantViewHolder(linearLayout,viewGroup);
        return RestaurantViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantAdapter.RestaurantViewHolder shoppingViewHolder, int i) {
        TextView item = shoppingViewHolder.subview.findViewById(R.id.restName);
        Button bb=shoppingViewHolder.subview.findViewById(R.id.button8);
        bb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent iui=new Intent(shoppingViewHolder.context, LoginPage.class);
                //iui.putExtra("restName",ss);
                shoppingViewHolder.context.startActivity(iui);
            }
        });
        //TextView itemprice = shoppingViewHolder.subview.findViewById(R.id.prc2);
        View xx=shoppingViewHolder.subview.findViewById(i);
        final String ss=restaurantModel.restaurantList.get(i).RestaurantName.toString();
        item.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent iui=new Intent(shoppingViewHolder.context, RestaurantDetails.class);
                iui.putExtra("restName",ss);
                shoppingViewHolder.context.startActivity(iui);
            }
        });
        item.setText(restaurantModel.restaurantList.get(i).RestaurantName);
        //itemprice.setText(String.valueOf(restaurantModel.restaurantList.get(i).price));
    }

    @Override
    public int getItemCount() {
        return restaurantModel.restaurantList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout subview;
        private final Context context;
        public RestaurantViewHolder(LinearLayout layout,ViewGroup itemView) {
            super(layout);
            subview = layout;
            context=itemView.getContext();
        }
    }
}
