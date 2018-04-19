package com.nadim.almourabi.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nadim.almourabi.R;
import com.nadim.almourabi.Student;
import com.nadim.almourabi.Teacher;
import com.nadim.almourabi.adapters.TeacherList;
import com.nadim.almourabi.adapters.TeacherListAdapter;

import java.util.ArrayList;
import java.util.List;

import helpers.studentTeacherR;


/**
 * A simple {@link Fragment} subclasss.
 */
public class Home extends Fragment {

    //Declare the student_LG var with empty char.
    public String student_LG = "";
    //Get our Fun class.
    studentTeacherR myFun = new studentTeacherR();
    private TeacherListAdapter mAdapter;
    //Get the adapter and the teacherList
    private List<TeacherList> teacherLists = new ArrayList<>();

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //RecyclerVIew
        RecyclerView recyclerView = view.findViewById(R.id.teacherRecycler);

        mAdapter = new TeacherListAdapter(teacherLists);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext()) {
            @Override
            //Remove the scroll from the recyclerView
            public boolean canScrollVertically() {
                return false;
            }
        };

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Set the adapter to the recyclerView
        recyclerView.setAdapter(mAdapter);

        // A sign out button to sign out from the current student
        Button logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
            }
        });

        //Get instance from the database and the Auth
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        final DatabaseReference myRef = database.getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        //GET CURRENT STUDENT
        ref.child("students").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /* This method is called once with the initial value and again
                 * whenever data at this location is updated.
                 */
                Student student = dataSnapshot.getValue(Student.class);
                student_LG = student.LG;

                //GET THE TEACHERS FOR THE CURRENT STUDENT
                myRef.child("teachers").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {

                        Teacher teacher = dataSnapshot.getValue(Teacher.class);
                        System.out.println("LG STUDENT " + student_LG);
                        assert teacher != null;
                        if (myFun.Searching(teacher.LGS, student_LG)) {
                            switch (myFun.getSubject(teacher.LGS, student_LG)) {
                                case "ARA":
                                    System.out.println("ARAB");
                                    teacher.LGS = "ARAB";
                                    break;
                                case "FRE":
                                    System.out.println("FRENCH");
                                    teacher.LGS = "FRENCH";
                                    break;
                                case "ANG":
                                    System.out.println("ANGLAIS");
                                    teacher.LGS = "ANGLAIS";
                                    break;
                                case "HIS":
                                    System.out.println("HISTORY");
                                    teacher.LGS = "HISTORY";
                                    break;
                                case "INF":
                                    System.out.println("COMPUTER SINCE");
                                    teacher.LGS = "COMPUTER SINCE";
                                    break;
                                default:
                                    System.out.println("NO SUBJECT");
                                    teacher.LGS = "NO SUBJECT";
                            }
                            prepareTeacherData(teacher.firstname, teacher.lastname, teacher.LGS);
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

        return view;

    }

    private void prepareTeacherData(String fn, String ln, String sub) {
        TeacherList teacherList = new TeacherList(fn, ln, sub);
        teacherLists.add(teacherList);

        //Notify the adapter that the data has been add.
        mAdapter.notifyDataSetChanged();
    }

}
