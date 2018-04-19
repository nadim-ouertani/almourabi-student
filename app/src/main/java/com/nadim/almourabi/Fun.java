package com.nadim.almourabi;

/**
 * Created by nadim on 4/19/18.
 * from tunisia with love
 */

public class Fun {

    Fun() {

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

}
