package com.nadim.almourabi.helpers;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by nadim on 4/19/18.
 * from tunisia with love
 */

public class studentTeacherR {

    public studentTeacherR() {

    }

    public Boolean Searching(String teacherLGS, String studentLG) {
        boolean res;
        res = teacherLGS.contains(studentLG) && studentLG.length() > 0;

        return res;
    }

    public String getSubject(String teacherLGS, String studentLG) {
        String res;
        int start = teacherLGS.indexOf(studentLG) + 3;
        int substart = start + 1;
        int subend = substart + 3;
        res = teacherLGS.substring(substart, subend);
        return res;
    }

    public boolean validateRegister(EditText firstname, EditText lastname, EditText email, EditText password) {
        boolean valid = true;


        String name = firstname.getText().toString();
        String lname = lastname.getText().toString();
        String vemail = email.getText().toString();
        String vpassword = password.getText().toString();


        if (name.isEmpty()) {
            firstname.setError("at least 3 characters");
            valid = false;
        } else {
            firstname.setError(null);
        }

        if (lname.isEmpty()) {
            lastname.setError("at least 3 characters");
            valid = false;
        } else {
            lastname.setError(null);
        }


        if (vemail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(vemail).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (vpassword.isEmpty() || password.length() < 4) {
            password.setError("at least 4 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    public boolean validateLogin(EditText email, EditText password) {
        boolean valid = true;

        String vemail = email.getText().toString();
        String vpassword = password.getText().toString();


        if (vemail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(vemail).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (vpassword.isEmpty() || password.length() < 4) {
            password.setError("at least 4 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

}
