package com.example.latitude.tomato;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class viewmenu extends AppCompatActivity implements LAdapter.Adderb, LAdapter.Adderi, LAdapter.Adderc {
    static ArrayList b_name;
    static ArrayList b_price;
    static ArrayList b_check;
    static ArrayList i_name;
    static ArrayList i_price;
    static ArrayList i_check;
    static ArrayList c_name;
    static ArrayList c_price;
    static ArrayList c_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmenu);
        //rest_id=getIntent().getExtras().getString("RESID");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton Next = findViewById(R.id.Next);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("BESTSELLERS"));
        tabLayout.addTab(tabLayout.newTab().setText("INDIAN"));  //TEXT TAB
        tabLayout.addTab(tabLayout.newTab().setText("CONTINENTAL"));  //TEXT TAB

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b_check == null && i_check == null && c_check == null){
                    Toast.makeText(getApplicationContext(),"Please add items to place order",Toast.LENGTH_SHORT).show();
                }
               else{
                Intent I = new Intent(viewmenu.this, Summary.class);
                I.putExtra("bestseller_name", b_name);
                I.putExtra("bestseller_price", b_price);
                I.putExtra("bestseller_check", b_check);
                I.putExtra("indian_name", i_name);
                I.putExtra("indian_price", i_price);
                I.putExtra("indian_check", i_check);
                I.putExtra("continental_name", c_name);
                I.putExtra("continental_price", c_price);
                I.putExtra("continental_check", c_check);
                startActivity(I);
            }}
        });

        final ViewPager viewPager =  findViewById(R.id.pager);

        final PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        final FragmentTransaction F = getSupportFragmentManager().beginTransaction();
        final Fragment f[] = new Fragment[3];
        viewPager.setCurrentItem(1);
        f[1] = adapter.getItem(1);
        F.add(R.id.pager, f[1]);
        viewPager.setCurrentItem(2);
        f[2] = adapter.getItem(2);
        F.add(R.id.pager, f[2]);
        viewPager.setCurrentItem(0);
        f[0] = adapter.getItem(0);
        F.add(R.id.pager, f[0]);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void Addb(ArrayList i_name, ArrayList i_price, ArrayList check) {
        b_name = i_name;
        b_price = i_price;
        b_check = check;
    }

    @Override
    public void Addi(ArrayList name, ArrayList price, ArrayList check) {
        i_name = name;
        i_price = price;
        i_check = check;
    }

    @Override
    public void Addc(ArrayList i_name, ArrayList i_price, ArrayList check) {
        c_name = i_name;
        c_price = i_price;
        c_check = check;
    }

}
