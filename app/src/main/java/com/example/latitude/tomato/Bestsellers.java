package com.example.latitude.tomato;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Bestsellers extends Fragment {

    public Bestsellers()
    {}

    ArrayList i_name = new ArrayList<>(Arrays.asList("Pizza","Bhaji","Pasta"));
    ArrayList i_price = new ArrayList<>(Arrays.asList("4.5","100","7"));

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Toast.makeText(getContext(), "bs", Toast.LENGTH_SHORT).show();

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.bs, container, false);
        RecyclerView VM_rv = (RecyclerView)view.findViewById(R.id.bs_recycle);
        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity().getApplicationContext());
        VM_rv.setLayoutManager(lm1);
        LAdapter la = new LAdapter(getContext(),i_name,i_price,0);
        VM_rv.setAdapter(la);

        return  view;
    }
}
