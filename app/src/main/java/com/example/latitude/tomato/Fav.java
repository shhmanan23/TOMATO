package com.example.latitude.tomato;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by LATITUDE on 03-04-2018.
 */

public class Fav extends Fragment {
    public Fav(){}

    public View onCreateView(LayoutInflater layoutInflater  , ViewGroup con , Bundle SBI)
    {
        View v = layoutInflater.inflate(R.layout.fav,con,false);
        RecyclerView F_rv = v.findViewById(R.id.fav_recycle);
        if(Str.User!=null){
        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity().getApplicationContext());
        F_rv.setLayoutManager(lm1);
        CustomAdapter CA = new CustomAdapter(getActivity().getApplicationContext(), false);
        F_rv.setAdapter(CA);}
        else {
            Toast.makeText(getContext(), "Please Login first to view your favourites.", Toast.LENGTH_SHORT).show();
        }

        return  v;
    }
}