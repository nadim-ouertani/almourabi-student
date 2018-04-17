package com.nadim.almourabi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by nadim on 2/13/18.
 * from tunisia with love
 */

class Student {

    String id, firstname, lastname, LG;

    public Student() {

    }

    Student(String id, String firstname, String lastname, String LG) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.LG = LG;
    }

    void writeNewUser(String userId, String firstname, String lastname, String LG) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Student student = new Student(id, firstname, lastname, LG);
        ref.child("students").child(userId).setValue(student);
    }

}
