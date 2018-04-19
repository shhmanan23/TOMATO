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

public class Indian extends Fragment {
    public Indian(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String rest_id = getActivity().getIntent().getExtras().getString("RESID");
        View view = inflater.inflate(R.layout.i, container, false);
        RecyclerView VM_rv = view.findViewById(R.id.i_recycle);
        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity().getApplicationContext());
        VM_rv.setLayoutManager(lm1);
        LAdapter la = new LAdapter(getContext(), rest_id, 1);
        VM_rv.setAdapter(la);

        return  view;
    }
}
