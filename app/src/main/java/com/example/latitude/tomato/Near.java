package com.example.latitude.tomato;

import android.content.Intent;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Near extends Fragment {

    public Near(){}

    public View onCreateView(LayoutInflater layoutInflater  , ViewGroup con , Bundle SBI)
    {
        View v = layoutInflater.inflate(R.layout.nearby,con,false);
       // RecyclerView F_rv = (RecyclerView)v.findViewById(R.id.fav_recycle);
        //LinearLayoutManager lm1 = new LinearLayoutManager(getActivity().getApplicationContext());
        //F_rv.setLayoutManager(lm1);
        //CustomAdapter CA = new CustomAdapter(getActivity().getApplicationContext());
        //F_rv.setAdapter(CA);
        Button viewnear = (Button)v.findViewById(R.id.NN);
        viewnear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getActivity(), GPS.class);
                startActivity(i);
            }
        });

        return  v;
    }
}

