package com.nadim.almourabi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class Evaluation extends Fragment {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    TextView notification;
    private FirebaseAuth mAuth;

    public Evaluation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evaluation, container, false);

        DatabaseReference ref = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        notification = view.findViewById(R.id.notification);
        ref.child("eva").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Eva eva = dataSnapshot.getValue(Eva.class);
                assert eva != null;
                if (eva.Sid.equals(mAuth.getCurrentUser().getUid())) {
                    //String key = dataSnapshot.getKey();
                    notification.setText("Your eva: " + eva.Eval + " Sub: " + eva.Sub + " From teacher: " + eva.Tid + " Your id: " + eva.Sid);
                    System.out.println("Notification found!");
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

}
