package com.nadim.almourabi;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //viewPager
    private ViewPager viewPager;

    //Firebase Auth
    private FirebaseAuth mAuth;

    //navigationBottom
    BottomNavigationView bottomNavigationView;

    //Fragments
    Home home;
    Notification notification;
    Profile profile;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide action bar
        getSupportActionBar().hide();

        //Get FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        //Initializing viewPager
        viewPager = findViewById(R.id.pagerAdapter);

        //Set custom animation for the viewpager

        //Dont refresh data from databse every time we swap between views
        viewPager.setOffscreenPageLimit((3) - 1);

        //Initializing the bottomNavigationView
        bottomNavigationView = findViewById(R.id.navigationBottom);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        FragmentTransaction ft;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(0, false);
                                break;
                            case R.id.navigation_notifications:
                                viewPager.setCurrentItem(1, false);
                                break;
                            case R.id.navigation_profile:
                                viewPager.setCurrentItem(2, false);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "Page selected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        setupViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        pagerAdapter adapter = new pagerAdapter(getSupportFragmentManager());
        home = new Home();
        notification = new Notification();
        profile = new Profile();
        adapter.addFragment(home);
        adapter.addFragment(notification);
        adapter.addFragment(profile);
        viewPager.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, login.class));
            finish();
        }
    }

}

