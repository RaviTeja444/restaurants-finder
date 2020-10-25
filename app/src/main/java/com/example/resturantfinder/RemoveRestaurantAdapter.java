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

public class RemoveRestaurantAdapter extends RecyclerView.Adapter<RemoveRestaurantAdapter.RestaurantViewHolder> {

    RestaurantModel  restaurantModel ;
    public RemoveRestaurantAdapter() {
        super();
        restaurantModel = restaurantModel.getSingleton();
    }
    @NonNull
    @Override
    public RemoveRestaurantAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_user_fav_list,viewGroup,false);
        RemoveRestaurantAdapter.RestaurantViewHolder RestaurantViewHolder = new RemoveRestaurantAdapter.RestaurantViewHolder(linearLayout,viewGroup);
        return RestaurantViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RemoveRestaurantAdapter.RestaurantViewHolder shoppingViewHolder, int i) {
        TextView item = shoppingViewHolder.subview1.findViewById(R.id.restName);
        Button bb=shoppingViewHolder.subview1.findViewById(R.id.button8);
        bb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent iui=new Intent(shoppingViewHolder.context, LoginPage.class);
                //iui.putExtra("restName",ss);
                shoppingViewHolder.context.startActivity(iui);
            }
        });
        //TextView itemprice = shoppingViewHolder.subview.findViewById(R.id.prc2);
        View xx=shoppingViewHolder.subview1.findViewById(i);
        final String ss=restaurantModel.restaurantList.get(i).Name.toString();
        item.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent iui=new Intent(shoppingViewHolder.context, RestaurantDetails.class);
                iui.putExtra("restName",ss);
                shoppingViewHolder.context.startActivity(iui);
            }
        });
        item.setText(restaurantModel.restaurantList.get(i).Name);
        //itemprice.setText(String.valueOf(restaurantModel.restaurantList.get(i).price));
    }

    @Override
    public int getItemCount() {
        return restaurantModel.restaurantList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout subview1;
        public final Context context;
        public RestaurantViewHolder(LinearLayout layout,ViewGroup itemView) {
            super(layout);
            subview1 = layout;
            context=itemView.getContext();
        }
    }
}
