package com.example.resturantfinder.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.resturantfinder.R;

import com.example.resturantfinder.adapter.UserRestaurantAdapter;
import com.example.resturantfinder.model.Restaurant;
import com.example.resturantfinder.utils.MainAcitivtyListner;
import com.example.resturantfinder.utils.MyConstants;
import com.example.resturantfinder.utils.ServerUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class userRestoListActivity extends AppCompatActivity  implements MainAcitivtyListner {

    private RecyclerView recyclerView= null;
    private boolean addminView =false;
    private ArrayList<Restaurant> restaurantList;
    private ArrayList<String> favRestaurantList;
    private String favListValue,key,uid;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_after_login);
        if(getIntent().getBooleanExtra(MyConstants.isFromAdmin,false)){
            addminView=getIntent().getBooleanExtra(MyConstants.isFromAdmin,false);
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user != null ? user.getUid() : null;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //readData();
        readFavData();
        recyclerView=findViewById(R.id.recyclerViewUsers);



        Log.d("flow", "userRestoListActivity : " );
        Button b=findViewById(R.id.userinfo);
        if(addminView){
            b.setText(("HELLO ADMIN"));
        }
        else
            b.setText(("HELLO USER"));

    }

    private void readFavData() {
        favRestaurantList =new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));
        JsonArrayRequest request = new JsonArrayRequest(ServerUtils.favRestaurantList+uid+".json", new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
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
                    }
                }

                Log.d("readFavData", "favRestaurantList : " + favRestaurantList);
                readData();

                Log.d("readFavData", "favRestaurantList : " + favRestaurantList.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("readFavData", "error : " + error);
                readData();
            }
        });
        queue.add(request);
    }

    private void readData() {
        Log.d("flow", "readData : " );
        restaurantList =new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));
        JsonArrayRequest request = new JsonArrayRequest(ServerUtils.restaurantList, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("ResporeadData", "response : " + response);
                Log.d("Responsfdfsde267", "favRestaurantList : " + favRestaurantList);
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
                        for (int j = 0; j< favRestaurantList.size(); j++) {
                            try{
                                String favRest = favRestaurantList.get(j);
                                Log.d("matchsdfs", "favRest : " + favRest);
                                Log.d("matchsdfs", "restaurant.getRestaurantId() : " + restaurant.getRestaurantId());
                                if(restaurant.getRestaurantId().equalsIgnoreCase(favRest)){
                                    restaurant.setIsfav(true);
                                    break;
                                }else {
                                    restaurant.setIsfav(false);
                                }

                            }catch (Exception e){
                                Log.d("matchsdfs", "Exception : " + e);
                            }
                        }
                        Log.d("matchsdfs", "isIsfav : " + restaurant.isIsfav());
                        if(!obj.getString(MyConstants.restaurantPositon).equalsIgnoreCase(MyConstants.restaurantDel)){
                            restaurantList.add(restaurant);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Response267", "JSONException : " + e);
                    }
                }
                UserRestaurantAdapter adapter = new UserRestaurantAdapter(getApplicationContext(),restaurantList, userRestoListActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                        RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
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

    public void goToFilterPage(View v){
        Intent filterIntent=new Intent(getApplicationContext(), ChooseFilterActivity.class);
        startActivity(filterIntent);
    }

    @Override
    public void click(Restaurant restaurant) {
        getListSize(restaurant);
    }

    @Override
    public void delFromFav(final Restaurant restaurant) {
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));
        JsonArrayRequest request = new JsonArrayRequest(ServerUtils.favRestaurantList+uid+".json", new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int j=0;j<response.length();j++) {
                    try {
                        String favKey = response.getString(j);
                        if(!favKey.equalsIgnoreCase(MyConstants.notFav)){
                            if(favKey.equalsIgnoreCase(restaurant.getRestaurantId())){
                               String s=j+"";
                                setNotfavvale(s);
                            }

                        }
                    } catch (JSONException e) {
                        Log.d("delFromFav", "JSONException : " + e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("delFromFav", "error : " + error);
            }
        });
        queue.add(request);

    }

    @Override
    public void restaurantDetails(Restaurant restaurant) {
        Intent intent =new Intent(getApplicationContext(), RestaurantDetailsActivity.class);
        intent.putExtra(MyConstants.restaurantValue,restaurant);
        startActivity(intent);

    }

    private void setNotfavvale(String s) {

        try {

            databaseReference.child(MyConstants.FAVLIST).child(uid).child(s).setValue(MyConstants.notFav)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                readFavData();
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d("adfasdasd", "" + e);
        }

    }

    private void getListSize(final Restaurant restaurant) {
        try {
            databaseReference.child(MyConstants.FAVLIST_SIZE).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String s = String.valueOf(snapshot.getValue());
                        int setTourNo = Integer.parseInt(s);
                        favListValue = String.valueOf(setTourNo + 1);
                        addRestoFav(restaurant.getRestaurantId());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //setClickable(false);
                }
            });
        } catch (Exception e) {
            // setClickable(false);
        }
    }

    private void addRestoFav(String key) {
        try {

            databaseReference.child(MyConstants.FAVLIST).child(uid).child(favListValue).setValue(key)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                databaseReference.child(MyConstants.FAVLIST_SIZE).child(uid).setValue(favListValue)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d("adfasdasd", "isSuccessful: " );
                                                    readFavData();
                                                }
                                            }
                                        });
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d("adfasdasd", "" + e);
        }

    }
}