package de.haw.hamburg.stisys.core;

import java.util.Map;

/**
 * The FSB represents the FSB that extends the User class.
 */
class FSB extends User {


    private final String name;
    private final String password;
    private int id;

    public FSB(String name, String password) {
        super(name,password);
        this.name = name;
        this.password = password;
    }


    public void createCourseFSB(Course course) {

    }

    public void viewList(Course course) {

    }

    public void viewCourses(Course course) {

    }

    // Implemented any additional methods from User class and therefore ControlledObject interface.

    public String getCourseName() {
        return null;
    }

    public int getCredits() {
        return 0;
    }

    public void displayCourseInfo() {

    }

    public int getCourseID() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }


    @Override
    public Map<String, Object> authenticateUser(String username, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'authenticateUser'");
    }


    @Override
    public void setGrades(Student student, Course course, int grade) {
        throw new UnsupportedOperationException("Unimplemented method 'setGrades'");
    }


    @Override
    public void enrolllab(Lab lab) {
        throw new UnsupportedOperationException("Unimplemented method 'enrolllab'");
    }


    @Override
    public String getProfessor() {
        throw new UnsupportedOperationException("Unimplemented method 'getProfessor'");
    }


    @Override
    public void setLab(Lab lab) {
        throw new UnsupportedOperationException("Unimplemented method 'setLab'");
    }


    @Override
    public Course getCourse() {
        throw new UnsupportedOperationException("Unimplemented method 'getCourse'");
    }


    @Override
    public int getLabID() {
        throw new UnsupportedOperationException("Unimplemented method 'getLabID'");
    }


    @Override
    public String getLabName() {
        throw new UnsupportedOperationException("Unimplemented method 'getLabName'");
    }


    @Override
    public Professor getInstructor() {
        throw new UnsupportedOperationException("Unimplemented method 'getInstructor'");
    }


    @Override
    public boolean hasLabPVL() {
        throw new UnsupportedOperationException("Unimplemented method 'hasLabPVL'");
    }


    @Override
    public void setPVL(Student student, Lab lab, boolean pvl) {
        throw new UnsupportedOperationException("Unimplemented method 'setPVL'");
    }


    @Override
    public void enroll(Course course) {
        throw new UnsupportedOperationException("Unimplemented method 'enroll'");
    }


    @Override
    public void viewGrades() {
        throw new UnsupportedOperationException("Unimplemented method 'viewGrades'");
    }



}

