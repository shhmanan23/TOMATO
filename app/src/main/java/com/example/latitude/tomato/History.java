package com.example.latitude.tomato;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class History extends Fragment {
    public History(){}

    public View onCreateView(LayoutInflater layoutInflater  , ViewGroup con , Bundle SBI) {
        View v = layoutInflater.inflate(R.layout.fav,con,false);
        RecyclerView F_rv = v.findViewById(R.id.fav_recycle);
        if(Str.User!=null){
        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity().getApplicationContext());
        F_rv.setLayoutManager(lm1);
        //HistoryAdapter HA = new HistoryAdapter(getActivity().getApplicationContext(),r_id,o_time,o_sum);
        HistoryAdapter HA = new HistoryAdapter(getActivity().getApplicationContext());
        F_rv.setAdapter(HA);}
        else {
            Toast.makeText(getActivity(), "Please Login first to view your orders", Toast.LENGTH_SHORT).show();
        }

        return  v;
    }
}
