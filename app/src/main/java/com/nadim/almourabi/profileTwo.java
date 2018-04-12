package com.nadim.almourabi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class profileTwo extends Fragment {
    private static final String TAG = "profile_two";
    TextView firstname, lastname, level, field, id, email;

    public profileTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_two, container, false);

        firstname = v.findViewById(R.id.sfn);
        lastname = v.findViewById(R.id.sln);
        level = v.findViewById(R.id.sgroup);
        field = v.findViewById(R.id.slevel);
        email = v.findViewById(R.id.semail);
        id = v.findViewById(R.id.sid);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        if (user != null) {
            email.setText(user.getEmail().toString().trim());
            id.setText(user.getUid().toString().trim());
        }

        mDatabase.child("students").child(mAuth.getCurrentUser().getUid()).addValueEventListener
                (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Student student = dataSnapshot.getValue(Student.class);
                assert student != null;
                firstname.setText(student.firstname);
                lastname.setText(student.lastname);
                level.setText(student.LG);
                field.setText(student.LG);
                Log.d(TAG, "Value is: " + student);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        return v;
    }

}
