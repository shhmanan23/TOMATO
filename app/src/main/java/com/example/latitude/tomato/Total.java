package com.example.latitude.tomato;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class Total extends android.app.Fragment {
    ArrayList IT =new ArrayList(Arrays.asList("Krishna","Heli","Madhavi","Modi","Devisha"));

    ArrayList P =new ArrayList(Arrays.asList("Amazinggggggg","OMG yummy","ewwww","nice"," too good"));

    ArrayList Q =new ArrayList(Arrays.asList("4.3","4.7","1","3.2","4.5"));


    public View onCreateView(LayoutInflater lf, ViewGroup vg, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_forcomments);
        final View view = lf.inflate(R.layout.display, vg, false);



        RecyclerView rv=view.findViewById(R.id.rdisplay);
        LinearLayoutManager lm=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);
        TA ta=new TA(view.getContext(),IT,P,Q);
        rv.setAdapter(ta);

        return  view;
    }
}
