package com.example.latitude.tomato;

import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;


public class PageAdapter extends FragmentPagerAdapter {

    int mNumOfTabs;
    Bestsellers tab1 = new Bestsellers();
    Indian tab2 = new Indian();
    Continental tab3 = new Continental();
    public PageAdapter(FragmentManager fm, int NumofTabs) {
        super(fm);
        this.mNumOfTabs = NumofTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return tab1;
            case 1:

                return tab2;
            case 2:

                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}

