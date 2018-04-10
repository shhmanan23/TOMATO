package com.example.latitude.tomato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class rdetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdetails);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        String rest_name = b.getString("RESNAME");
        String rest_add = b.getString("RESADD");
        TextView resname = (TextView)findViewById(R.id.NAME);
        TextView resadd = (TextView)findViewById(R.id.AD);
        TextView resrate = (TextView)findViewById(R.id.RATING);
        Button view = (Button)findViewById(R.id.VIEWMENU);
        Button fav = (Button)findViewById(R.id.fav);
        Button rate = (Button)findViewById(R.id.REVIEW);
        resname.setText(rest_name);
        resadd.setText(rest_add);
        // write code to get data of address/rating from firebase

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }
}
