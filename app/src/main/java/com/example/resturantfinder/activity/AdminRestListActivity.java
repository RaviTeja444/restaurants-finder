package com.example.resturantfinder.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.android.volley.toolbox.Volley;
import com.example.resturantfinder.R;
import com.example.resturantfinder.adapter.AdminRestaurantListAdapter;
import com.example.resturantfinder.adapter.MainPageAdapter;
import com.example.resturantfinder.model.Restaurant;
import com.example.resturantfinder.utils.MainAcitivtyListner;
import com.example.resturantfinder.utils.MyConstants;
import com.example.resturantfinder.utils.ServerUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class AdminRestListActivity extends AppCompatActivity  implements MainAcitivtyListner {


    private RecyclerView recyclerView;
    private boolean addminView =true;
    private ArrayList<Restaurant> restaurantList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_rest_list);
        recyclerView=findViewById(R.id.recyclerView);
        readData();

    }

    private void readData() {

        restaurantList =new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));
        Log.d("ResporeadData", "ServerUtils : " + ServerUtils.restaurantList);
        JsonArrayRequest request = new JsonArrayRequest(ServerUtils.restaurantList, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                Log.d("ResporeadData", "response : " + response);
                // Parsing json
                if(response!=null){
                    for (int i = (response.length()-1); i >0; i--) {
                        try {

                            JSONObject obj = response.getJSONObject(i);
                            Restaurant restaurant = new Restaurant();
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
                            if(!obj.getString(MyConstants.restaurantPositon).equalsIgnoreCase(MyConstants.restaurantDel)){
                                restaurantList.add(restaurant);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Response267", "JSONException : " + e);
                        }

                    }
                    AdminRestaurantListAdapter adapter = new AdminRestaurantListAdapter(getApplicationContext(),restaurantList,AdminRestListActivity.this);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                            RecyclerView.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(getApplicationContext(), "No Restaurant is Added", Toast.LENGTH_LONG).show();
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
        // Adding request to request queue


    }



    public void goToAdminHomePage(View v){
        Intent adminHomePage=new Intent(getApplicationContext(), userHomeActivity.class);
        adminHomePage.putExtra(MyConstants.isFromAdmin,addminView);
        this.startActivity(adminHomePage);
    }

    public void goToAddRestPage(View v){
        Intent addrestPage=new Intent(getApplicationContext(), AddRestaurantActivity.class);
        startActivityForResult(addrestPage,MyConstants.restaurantaddedValue);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==MyConstants.restaurantaddedValue){
            if(data!=null){
                if(data.getBooleanExtra(MyConstants.restaurantadded,false)){
                    readData();
                }
            }
        }
    }


    @Override
    public void click(Restaurant restaurant) {

        try {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child(MyConstants.Restaurant).child(restaurant.getPosition())
                    .child(MyConstants.restaurantPositon.toString()).setValue(MyConstants.restaurantDel)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Restaurant is Deleted....", Toast.LENGTH_SHORT).show();
                                readData();
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d("adfasdasd", "" + e);
        }

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