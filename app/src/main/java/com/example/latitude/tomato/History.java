package com.example.latitude.tomato;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class History extends Fragment {
    public History(){}
   // ArrayList r_id = new ArrayList();
  //  ArrayList o_time = new ArrayList();
   // ArrayList o_sum = new ArrayList();
    ArrayList r_id = new ArrayList<>(Arrays.asList("Blue Roof Top","I love Sandwich House","MEOWWW"));
    ArrayList o_time = new ArrayList<>(Arrays.asList("9pm","3am","7pm"));
    ArrayList o_sum = new ArrayList<>(Arrays.asList("900","350","750"));




    public View onCreateView(LayoutInflater layoutInflater  , ViewGroup con , Bundle SBI) {
        View v = layoutInflater.inflate(R.layout.fav,con,false);
        RecyclerView F_rv = (RecyclerView)v.findViewById(R.id.fav_recycle);
        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity().getApplicationContext());
        F_rv.setLayoutManager(lm1);
        HistoryAdapter HA = new HistoryAdapter(getActivity().getApplicationContext(),r_id,o_time,o_sum);
        F_rv.setAdapter(HA);

        return  v;
    }
}
