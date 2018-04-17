package com.example.latitude.tomato;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class About extends Fragment {
    public About(){}

    public View onCreateView(LayoutInflater layoutInflater  , ViewGroup con , Bundle SBI)
    {
        View v = layoutInflater.inflate(R.layout.ab,con,false);
        return  v;
    }

}
