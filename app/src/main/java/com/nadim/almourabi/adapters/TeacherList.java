package com.nadim.almourabi.adapters;

/**
 * Created by nadim on 4/13/18.
 * from tunisia with love
 */
public class TeacherList {
    private String FirstName, LastName, Subject;

    public TeacherList() {

    }

    public TeacherList(String FirstName, String LastName, String Subject) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Subject = Subject;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String x) {
        this.FirstName = x;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String x) {
        this.LastName = x;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String x) {
        this.Subject = x;
    }
}
