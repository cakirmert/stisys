package de.haw.hamburg.stisys.core;

import java.util.Map;

/**
 * The Professor class represents a professor that extends the User class.
 */
class Professor extends User {
    private final String password;
    private int id;
    public Professor(String name, String password) {
        super(name,password);
        this.password = password;
    }

    // Implemented any additional methods from User class and therefore ControlledObject interface.

    public String getCourseName() {
        return null;
    }
    public int getId() {
        return id;
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

    public String getPassword() {
        return password;
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

    @Override
    public void createCourseFSB(Course course) {
        throw new UnsupportedOperationException("Unimplemented method 'createCourseFSB'");
    }

    @Override
    public void viewList(Course course) {
        throw new UnsupportedOperationException("Unimplemented method 'viewList'");
    }

    @Override
    public void viewCourses(Course course) {
        throw new UnsupportedOperationException("Unimplemented method 'viewCourses'");
    }

}
