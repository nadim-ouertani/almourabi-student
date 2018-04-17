package com.nadim.almourabi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends Fragment {


    public Notification() {
        // Required empty public constructor
    }

    public static Notification newInstance() {
        Notification notification = new Notification();
        Bundle args = new Bundle();
        notification.setArguments(args);
        return notification;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

}
