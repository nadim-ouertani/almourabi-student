package com.nadim.almourabi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Evaluation extends Fragment {
    private List<EvaList> evaLists = new ArrayList<>();
    private EvaListAdapter mAdapter;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;

    public Evaluation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evaluation, container, false);

        //RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.evaRecyclerView);
        mAdapter = new EvaListAdapter(evaLists);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext()) {
        };
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Set the adapter to the recyclerView
        recyclerView.setAdapter(mAdapter);

        //Get instance from the database and the Auth
        DatabaseReference ref = database.getReference();
        final DatabaseReference myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        //Point the ref var on the EVA child to fetch eva
        ref.child("eva").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //Get the eva class
                final Eva eva = dataSnapshot.getValue(Eva.class);

                //Search the eva tha have the same student id
                if (eva.Sid.equals(mAuth.getCurrentUser().getUid())) {

                    //Set the eval from the CHAR
                    switch (eva.Eval) {
                        case "B":
                            eva.Eval = "BAD";
                            break;
                        case "G":
                            eva.Eval = "GOOD";
                            break;
                        default:
                            eva.Eval = "No EVAL";
                    }

                    //Bind the view with the recyclerVIew
                    prepareEvaData("firstName", "lastName", eva.Sub, eva.Eval);
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

    private void prepareEvaData(String firstName, String lastName, String Subject, String Eva) {
        EvaList evaList = new EvaList(firstName, lastName, Subject, Eva);
        evaLists.add(evaList);

        //Notify the list that the data has been changed.
        mAdapter.notifyDataSetChanged();
    }

}

