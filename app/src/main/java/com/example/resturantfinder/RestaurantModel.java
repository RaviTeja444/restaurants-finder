package com.example.resturantfinder;



import android.support.annotation.NonNull;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantModel {

    public ArrayList<Restaurant> restaurantList;
    //public ArrayList<Restaurant> favrestaurantList;
    private static RestaurantModel theModel=null;
    FirebaseDatabase database=null;
    DatabaseReference reference=null;

        private RestaurantModel() {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("Restaurants")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Map<String, Object> book = document.getData();
//                                restaurantList.add(new RestaurantInfo(book.get("RestaurantName").toString(),"","","","",""));
//                                System.out.println( "rr "+document);
//                                //Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            //Log.w(TAG, "Error getting documents.", task.getException());
//                        }
//                    }
//                });


        //restaurantAdapter=new RestaurantAdapter();
        restaurantList = new ArrayList<RestaurantInfo>();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Restaurants");
        //restaurantList.add(new RestaurantInfo("ss","ff","g","ggg","ff","g"));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    try {
                        for(DataSnapshot r : dataSnapshot.getChildren()){
                            String RestaurantEmail="";
                            String RestaurantId="";
                            String RestaurantLocation="";
                            String RestaurantType="";
                            String Website="";
                            String RestaurantName="";
                            if(r.child("RestaurantEmail").getValue()!=null){
                                 RestaurantEmail=r.child("RestaurantEmail").getValue().toString();
                                Log.d("Resturant Debug: ", r.child("RestaurantEmail").getValue().toString() );
                            }
                            if(r.child("RestaurantId").getValue()!=null){
                                 RestaurantId=r.child("RestaurantId").getValue().toString();
                                Log.d("Resturant Debug: ", r.child("RestaurantId").getValue().toString() );
                            }
                            if(r.child("RestaurantLocation").getValue()!=null){
                                 RestaurantLocation=r.child("RestaurantLocation").getValue().toString();
                                Log.d("Resturant Debug: ", r.child("RestaurantLocation").getValue().toString());
                            }
                            if(r.child("RestaurantType").getValue()!=null){
                                 RestaurantType=r.child("RestaurantType").getValue().toString();
                                Log.d("Resturant Debug: ", r.child("RestaurantType").getValue().toString() );
                            }
                            if(r.child("Website").getValue()!=null){
                                 Website=r.child("Website").getValue().toString();
                                Log.d("Resturant Debug: ", r.child("Website").getValue().toString() );
                            }
                            if(r.child("RestaurantName").getValue()!=null){
                                 RestaurantName=r.child("RestaurantName").getValue().toString();
                                Log.d("Resturant Debug: ", r.child("RestaurantName").getValue().toString() );
                            }
                            //RestaurantInfo model = r.getValue(RestaurantInfo.class);
                            restaurantList.add(new RestaurantInfo(RestaurantEmail,RestaurantId,RestaurantLocation,RestaurantType,Website,RestaurantName));

                            Log.d("Resturant Debug: ", restaurantList.toString() );
                        }


                        //System.out.println("model: " + model.getRestaurantName());
                       // restaurantList.add(new RestaurantInfo(model.));
                        //mRecyclerView.scrollToPosition(mChats.size() - 1);
                        //mAdapter.notifyItemInserted(mChats.size() - 1);
                    } catch (Exception ex) {
                        //Log.e(TAG, ex.getMessage());
                        Log.e("Restuarant Error:", ex.getLocalizedMessage());
                        System.out.println("vvvvvv"+ex.getMessage());
                    }
                }

                if(restaurantAdapter != null){
                    Log.d("notified ",restaurantAdapter.toString());
                    restaurantAdapter.notifyDataSetChanged();
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                reference=null;
            }


        });
    }
       public static RestaurantModel getSingleton() {
        if (theModel == null)
            theModel = new RestaurantModel();
        return theModel;
    }

    private void addRest(ArrayList<RestaurantInfo> array) {
        for (RestaurantInfo r : array) {
            restaurantList.add(r);
        }


    }
}
