package com.example.latitude.tomato;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class displayreview extends Fragment {

    ArrayList username=new ArrayList(Arrays.asList("Krishna","Heli","Madhavi","Modi","Devisha"));

    ArrayList rev =new ArrayList(Arrays.asList("Amazinggggggg","OMG yummy","ewwww","nice"," too good"));

    ArrayList ratingss =new ArrayList(Arrays.asList("4.3","4.7","1","3.2","4.5"));

    public View onCreateView(LayoutInflater lf, ViewGroup vg, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_forcomments);
        View view=lf.inflate(R.layout.display,vg,false);
        RecyclerView rv=(RecyclerView)view.findViewById(R.id.rdisplay);
        LinearLayoutManager lm=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);
        DA da=new DA(view.getContext(),username,rev,ratingss);
        rv.setAdapter(da);
        return view;
    }

}
