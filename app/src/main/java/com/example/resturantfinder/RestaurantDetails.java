package com.example.resturantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RestaurantDetails extends AppCompatActivity {

    public String restName=null;
    public String restloc1=null;
    public String resttype=null;
    public String restweb1=null;
    public String restemail1=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_list);
        Intent g=getIntent();
        restName=g.getStringExtra("restName");
        TextView restView=findViewById(R.id.textView10);
        restView.setText(restName);

        restloc1=g.getStringExtra("restlocation");
        TextView restloc=findViewById(R.id.textView23);
        restloc.setText(restloc1);

        resttype=g.getStringExtra("resttype");
        TextView resttyp=findViewById(R.id.textView26);
        resttyp.setText(resttype);

        restweb1=g.getStringExtra("restwebsite");
        TextView restweb=findViewById(R.id.textView29);
        restweb.setText(restweb1);

        restemail1=g.getStringExtra("restemail");
        TextView restemail=findViewById(R.id.textView25);
        restemail.setText(restemail1);

        //iui.putExtra("restid",id);

    }
    public void goToFeedbackPage(View v){
        Intent filterIntent=new Intent(this,feedback.class);
        this.startActivity(filterIntent);
    }

    public void goBackToStartupPage(View v){
        Intent filterIntent=new Intent(this, MainActivity.class);
        this.startActivity(filterIntent);
    }
}