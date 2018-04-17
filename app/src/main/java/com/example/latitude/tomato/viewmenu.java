package com.example.latitude.tomato;

import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class viewmenu extends AppCompatActivity {
    android.support.v4.app.FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmenu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("BESTSELLERS"));
        tabLayout.addTab(tabLayout.newTab().setText("INDIAN"));  //TEXT TAB
        tabLayout.addTab(tabLayout.newTab().setText("CONTINENTAL"));  //TEXT TAB

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        final PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition())
                {
                    case 0:
                        Fragment f=adapter.getItem(tab.getPosition());
                        getSupportFragmentManager().beginTransaction().add(R.id.pager,f).commit();
                        break;
                    case 1:
                        Fragment f1=adapter.getItem(tab.getPosition());
                        getSupportFragmentManager().beginTransaction().add(R.id.pager,f1).commit();
                        break;
                    case 2:
                        Fragment f2=adapter.getItem(tab.getPosition());
                        getSupportFragmentManager().beginTransaction().add(R.id.pager,f2).commit();
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                }





            @Override
            public void onTabReselected(TabLayout.Tab tab) {}

        });
        }
    
    
}
