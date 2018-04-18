package com.nadim.almourabi;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends Fragment {
    public TabLayout tabLayout;
    public ViewPager viewPager;
    public notificationPagerAdapter notificationPagerAdapter;

    public Notification() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        viewPager = view.findViewById(R.id.notificationViewPager);
        notificationPagerAdapter = new notificationPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(notificationPagerAdapter);

        tabLayout = view.findViewById(R.id.notificationTabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
