package model;

import java.util.ArrayList;

public class StudentClass {

    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private ArrayList<String> courses;

    //constructor
    public StudentClass() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String program) {
        this.programme = program;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }
}
