package com.nadim.almourabi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by nadim on 3/1/18.
 * from tunisia with love
 */

public class pagerAdapter extends FragmentPagerAdapter {

    //private String[] title = new String[]{"Home", "Todo", "Profile"};

    pagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Home();
            case 1:
                return new Todo();
            case 2:
                return new Profile();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
