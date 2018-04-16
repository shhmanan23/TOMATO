package com.example.latitude.tomato;

import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
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
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.pager,new Bestsellers());
                        ft.commit();
                        break;
                    case 1:
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.pager,new Indian());
                        ft.commit();
                        break;
                    case 2:
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.pager,new Continental());
                        ft.commit();
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
    
    
}
