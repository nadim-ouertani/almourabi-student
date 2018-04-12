package com.nadim.almourabi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nadim.almourabi.Eva;
import com.nadim.almourabi.R;
import com.nadim.almourabi.Student;
import com.nadim.almourabi.Teacher;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    public String student_LG = "";
    private FirebaseAuth mAuth;
    TextView not;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button logout = view.findViewById(R.id.logout);
        not = view.findViewById(R.id.notification);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        final DatabaseReference myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        //GET CURRENT STUDENT
        ref.child("students").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Student student = dataSnapshot.getValue(Student.class);
                System.out.println("First And Last NAME : " + student.firstname + " " + student.lastname + " | Student Level and group : " + student.LG);
                student_LG = student.LG;//GET THE TEACHERS FOR THE CURRENT STUDENT

                myRef.child("teachers").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {

                        Teacher teacher = dataSnapshot.getValue(Teacher.class);
                        System.out.println("LG STUDENT " + student_LG);
                        if (searching(teacher.LGS,student_LG)) {
                            System.out.println("A teacher found!: " + teacher.firstname + " YOUR LG : " + student_LG + " YOUR TEACHER LGS : " + teacher.LGS);
                            switch (getSub(teacher.LGS,student_LG)){
                                case "ARA":
                                    System.out.println("SUBJECT ARAB");
                                    break;
                                case "FRE":
                                    System.out.println("SUBJECT FRENCH");
                                    break;
                                case "ANG":
                                    System.out.println("SUBJECT ANGLAIS");
                                    break;
                                case "HIS":
                                    System.out.println("SUBJECT HISTORY");
                                    break;
                                case "INF":
                                    System.out.println("SUBJECT COMPUTER SINCE");
                                    break;
                                default:
                                    System.out.println("NO SUBJECT");
                            }
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //todo
        ref.child("eva").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Eva eva = dataSnapshot.getValue(Eva.class);
                if (eva.Sid.equals(mAuth.getCurrentUser().getUid())){
                    String key = dataSnapshot.getKey();
                    not.setText("Your eval is: " + eva.Eval + " From Teacher id: " + eva.Tid + "EVA ID : " + key);
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

    //FUN
    public Boolean searching(String ts, String ss){
        boolean res;
        if (ts.contains(ss) && ss.length() > 0) {
            res = true;
        }
        else{
            res = false;
        }

        return res;
    }

    public String getSub(String s, String ss){
        String res = "";
        int start = s.indexOf(ss) + 3;
        int substart = start+1;
        int subend = substart+3;
        res = s.substring(substart,subend);
        return res;
    }

}
