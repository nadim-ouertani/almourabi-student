package com.nadim.almourabi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by nadim on 3/11/18.
 * from tunisia with love
 */

public class pagerAdapterProfile extends FragmentPagerAdapter {

    pagerAdapterProfile(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new profileTwo();
            case 1:
                return new Notification();
            case 2:
                return new Settings();
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

