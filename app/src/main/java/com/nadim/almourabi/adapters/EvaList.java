package com.nadim.almourabi.adapters;

/**
 * Created by nadim on 4/18/18.
 * from tunisia with love
 */
public class EvaList {
    private String FirstName, LastName, Subject, Eva;

    public EvaList() {
        //Empty constructor.
    }

    public EvaList(String FirstName, String LastName, String Subject, String Eva) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Subject = Subject;
        this.Eva = Eva;
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

    public String getEva() {
        return Eva;
    }

    public void setEva(String x) {
        this.Eva = x;
    }
}

