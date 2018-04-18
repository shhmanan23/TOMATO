package com.example.latitude.tomato;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class viewmenu extends AppCompatActivity implements LAdapter.Adderb, LAdapter.Adderi, LAdapter.Adderc {
    static ArrayList b_name;
    static ArrayList b_price;
    static boolean[] b_check;
    static ArrayList i_name;
    static ArrayList i_price;
    static boolean[] i_check;
    static ArrayList c_name;
    static ArrayList c_price;
    static boolean[] c_check;
    LAdapter lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmenu);

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
            }
        });

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        final PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        Fragment f = adapter.getItem(tab.getPosition());
                        getSupportFragmentManager().beginTransaction().add(R.id.pager, f).commit();
                        break;
                    case 1:
                        Fragment f1 = adapter.getItem(tab.getPosition());
                        getSupportFragmentManager().beginTransaction().add(R.id.pager, f1).commit();
                        break;
                    case 2:
                        Fragment f2 = adapter.getItem(tab.getPosition());
                        getSupportFragmentManager().beginTransaction().add(R.id.pager, f2).commit();
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }


            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });
    }

    @Override
    public void Addb(ArrayList i_name, ArrayList i_price, boolean[] check) {
        b_name = i_name;
        b_price = i_price;
        b_check = check;
    }

    @Override
    public void Addi(ArrayList name, ArrayList price, boolean[] check) {
        i_name = name;
        i_price = price;
        i_check = check;
    }

    @Override
    public void Addc(ArrayList i_name, ArrayList i_price, boolean[] check) {
        c_name = i_name;
        c_price = i_price;
        c_check = check;
    }
}
