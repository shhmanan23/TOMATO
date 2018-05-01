package com.example.latitude.tomato;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class O_S extends AppCompatActivity {
    TextView amount, ordernum , rest , dt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o__s);
        amount = (TextView)findViewById(R.id.total);
        ordernum = findViewById(R.id.orderid);
        rest = findViewById(R.id.restname);
        dt = findViewById(R.id.datetime);
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        Total tt = new Total();
        ft.replace(R.id.frag,new Total());

    //    set text for text view
    }
}
