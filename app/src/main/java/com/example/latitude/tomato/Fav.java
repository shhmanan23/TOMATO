package com.example.latitude.tomato;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LATITUDE on 03-04-2018.
 */

public class Fav extends Fragment {
    public Fav(){}

    public View onCreateView(LayoutInflater layoutInflater  , ViewGroup con , Bundle SBI)
    {
        return layoutInflater.inflate(R.layout.fav,con,false);
    }
}
