package com.nadim.almourabi;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {


    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        //FirebaseAuth mAuth = FirebaseAuth.getInstance();

        ViewPager pager = v.findViewById(R.id.pager_profile);
        pagerAdapterProfile myAdapter = new pagerAdapterProfile(getChildFragmentManager());
        pager.setAdapter(myAdapter);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = v.findViewById(R.id.tabs_profile);
        tabLayout.setupWithViewPager(pager);
        seticon(tabLayout);
        return v;
    }

    private void seticon(TabLayout tab){
        tab.getTabAt(0).setIcon(R.drawable.home);
        tab.getTabAt(1).setIcon(R.drawable.todo);
        tab.getTabAt(2).setIcon(R.drawable.profile);
    }


}
