package com.example.resturantfinder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;;
import com.example.resturantfinder.R;
import com.example.resturantfinder.adapter.FavouriteRestaurantAdapter;
import com.example.resturantfinder.model.Restaurant;
import com.example.resturantfinder.utils.MainAcitivtyListner;
import com.example.resturantfinder.utils.MyConstants;
import com.example.resturantfinder.utils.ServerUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class FavoriteRestaurantListActivity extends AppCompatActivity implements MainAcitivtyListner {


    private RecyclerView recyclerView= null;
    private boolean addminView =false;
    private String uid;
    private ArrayList<Restaurant> restaurantList;
    private ArrayList<String> favRestaurantList;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_rest_list);
        if(getIntent().getBooleanExtra(MyConstants.isFromAdmin,false)){
            addminView=getIntent().getBooleanExtra(MyConstants.isFromAdmin,false);
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user != null ? user.getUid() : null;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        readFavData();
        recyclerView=findViewById(R.id.recyclerViewfavlist);

    }

    private void readFavData() {
        favRestaurantList =new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));
        Log.d("readFavData", "ServerUtils : " + ServerUtils.favRestaurantList+uid+".json");
        JsonArrayRequest request = new JsonArrayRequest(ServerUtils.favRestaurantList+uid+".json", new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    Log.d("readFavData", "response : " + response);
                    for (int j=0;j<response.length();j++) {
                        try {
                            String favKey = response.getString(j);
                            Log.d("readFavData", "favKey : " + favKey);
                            if(!favKey.equalsIgnoreCase(MyConstants.notFav)){
                                favRestaurantList.add(favKey) ;
                            }
                        } catch (JSONException e) {
                            //  e.printStackTrace();
                            Log.d("readFavData", "JSONException : " + e);
                            Toast.makeText(getApplicationContext(), "No Favourite Restaurant is Added", Toast.LENGTH_LONG).show();
                        }
                    }
                    readData();
                    Log.d("readFavData", "favRestaurantList : " + favRestaurantList.size());
                }else {
                    Toast.makeText(getApplicationContext(), "No Favourite Restaurant is Added", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("readFavData", "error : " + error);
                Toast.makeText(getApplicationContext(), "No Favourite Restaurant is Added", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }

    private void readData() {

        restaurantList =new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));
        JsonArrayRequest request = new JsonArrayRequest(ServerUtils.restaurantList, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("ResporeadData", "response : " + response);
                Log.d("Responsfdfsde267", "favRestaurantList : " + favRestaurantList);
                if(response!=null){
                    for (int i = (response.length()-1); i >0; i--) {
                        try {
                            JSONObject obj = response.getJSONObject(i);
                            Restaurant restaurant = new Restaurant();

                            if(!obj.getString(MyConstants.restaurantPositon).equalsIgnoreCase(MyConstants.restaurantDel)){

                                restaurant.setPosition(obj.getString(MyConstants.restaurantPositon));
                                restaurant.setRestaurantName(obj.getString(MyConstants.restaurantName));
                                restaurant.setRestaurantCity(obj.getString(MyConstants.restaurantCity));
                                restaurant.setRestaurantState(obj.getString(MyConstants.restaurantState));
                                restaurant.setRestaurantId(obj.getString(MyConstants.restaurantId));
                                restaurant.setRestaurantfoodStyle(obj.getString(MyConstants.restaurantfoodStyle));
                                restaurant.setRestaurantTiming(obj.getString(MyConstants.restaurantTiming));
                                restaurant.setRestaurantcontactNo(obj.getString(MyConstants.restaurantcontactNo));
                                restaurant.setRestaurantemail(obj.getString(MyConstants.restaurantemail));
                                restaurant.setRestaurantorderlink(obj.getString(MyConstants.restaurantorderlink));
                                restaurant.setZipCode(obj.getString(MyConstants.restaurantzipCode));
                                Log.d("matchsdfs", "isIsfav : " + restaurant.isIsfav());
                                for (int j = 0; j< favRestaurantList.size(); j++) {
                                    try{
                                        String favRest = favRestaurantList.get(j);
                                        Log.d("matchsdfs", "favRest : " + favRest);
                                        Log.d("matchsdfs", "restaurant.getRestaurantId() : " + restaurant.getRestaurantId());
                                        if(restaurant.getRestaurantId().equalsIgnoreCase(favRest)){
                                            restaurantList.add(restaurant);
                                            break;
                                        }
                                    }catch (Exception e){
                                        Log.d("matchsdfs", "Exception : " + e);
                                    }
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Response267", "JSONException : " + e);
                        }
                        FavouriteRestaurantAdapter adapter = new FavouriteRestaurantAdapter(getApplicationContext(),restaurantList,
                                FavoriteRestaurantListActivity.this);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                                RecyclerView.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                    }
                }

                Log.d("ResporeadData", "restaurantList : " + restaurantList.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response267", "error : " + error);
            }
        });
        queue.add(request);
    }



    public void goBackToHome(View v){
        Intent userHomePage=new Intent(getApplicationContext(), userHomeActivity.class);
        userHomePage.putExtra(MyConstants.isFromAdmin,addminView);
        startActivity(userHomePage);
    }

    @Override
    public void click(Restaurant restaurant) {

    }

    @Override
    public void delFromFav(Restaurant restaurant) {

    }

    @Override
    public void restaurantDetails(Restaurant restaurant) {
        Intent intent =new Intent(getApplicationContext(), RestaurantDetailsActivity.class);
        intent.putExtra(MyConstants.restaurantValue,restaurant);
        startActivity(intent);

    }
}