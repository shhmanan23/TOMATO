package com.example.latitude.tomato;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Near extends Fragment {

    public Near(){}

    public View onCreateView(LayoutInflater layoutInflater  , ViewGroup con , Bundle SBI)
    {
        View v = layoutInflater.inflate(R.layout.fav,con,false);
        RecyclerView F_rv = (RecyclerView)v.findViewById(R.id.fav_recycle);
        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity().getApplicationContext());
        F_rv.setLayoutManager(lm1);
        CustomAdapter CA = new CustomAdapter(getActivity().getApplicationContext());
        F_rv.setAdapter(CA);

        return  v;
    }
}

