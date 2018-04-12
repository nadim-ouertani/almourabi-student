package com.nadim.almourabi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by nadim on 4/2/18.
 * from tunisia with love
 */
public class Eva {

    String Tid, Sid, Eval, Sub;

    public Eva(){

    }

    Eva(String Tid, String Sid, String Eval, String Sub){
        this.Tid = Tid;
        this.Sid = Sid;
        this.Eval = Eval;
        this.Sub = Sub;
    }

    void writeNewEva(String Tid, String Sid, String Eval, String Sub) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("eva").push().setValue(new Eva(Tid, Sid, Eval, Sub));

    }
}
