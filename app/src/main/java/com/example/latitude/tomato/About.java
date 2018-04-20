package com.example.latitude.tomato;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class About extends Fragment {
    public About(){}
        ImageView iv;
    Animation animation;
    public View onCreateView(LayoutInflater layoutInflater  , ViewGroup con , Bundle SBI)
    {
        View v = layoutInflater.inflate(R.layout.ab,con,false);
        iv = v.findViewById(R.id.image);

        animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.scale);
        iv.startAnimation(animation);



        return v;
    }

}
