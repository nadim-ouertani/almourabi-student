package com.nadim.almourabi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nadim on 4/12/18.
 * from tunisia with love
 */
public class pagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> myFragmentList = new ArrayList<>();

    pagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return myFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return myFragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        myFragmentList.add(fragment);
    }

}
