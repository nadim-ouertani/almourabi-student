package com.nadim.almourabi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by nadim on 3/25/18.
 * from tunisia with love
 */

public class Teacher {
    String firstname;
    String lastname;
    String LGS;

    public Teacher() {

    }

    Teacher(String firstname, String lastname, String LGS) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.LGS = LGS;
    }

    void writeNewUser(String userId, String firstname, String lastname, String LGS) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Teacher teacher = new Teacher(firstname, lastname, LGS);
        ref.child("teachers").child(userId).setValue(teacher);
    }
}
