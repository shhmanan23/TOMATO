package com.example.latitude.tomato;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.ImageButton;
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

        final TextView resname = findViewById(R.id.NAME);

        TextView resadd = findViewById(R.id.AD);

        TextView resrate = findViewById(R.id.RATING);

        Button view = findViewById(R.id.VIEWMENU);

        ImageButton fav = findViewById(R.id.FAV);

        Button rate = findViewById(R.id.REVIEW);

        resname.setText(rest_name);

        resadd.setText(rest_add);

        // write code to get data of address/rating from firebase


        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        displayreview c=new displayreview();
        ft.add(R.id.fragment,new displayreview());
        ft.commit();
        view.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent in = new Intent(rdetails.this, viewmenu.class);
                startActivity(in);

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


                Intent in = new Intent(rdetails.this, rate.class);
                String n = resname.getText().toString();
                in.putExtra("R",n);
                startActivity(in);


            }

        });





    }

}