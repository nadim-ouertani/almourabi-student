package com.nadim.almourabi.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nadim.almourabi.fragments.Evaluation;
import com.nadim.almourabi.fragments.Todo;

/**
 * Created by nadim on 4/18/18.
 * from tunisia with love
 */
public class notificationPagerAdapter extends FragmentPagerAdapter {
    private final static int PAGE = 2;

    public notificationPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Todo();
            case 1:
                return new Evaluation();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home work";
            case 1:
                return "Eva";
            default:
                return null;
        }
    }
}
