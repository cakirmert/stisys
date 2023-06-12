package de.haw.hamburg.stisys.core;

import java.util.Map;

/**
 * The Student class represents a student that extends the User class.
 */
class Student extends User {


    private final String name;
    private final String password;
    private int id;

    public Student(String name, String password) {
        super(name,password);
        this.name = name;
        this.password = password;
    }

    /**
     * Enrolls the student in a course.
     * @param course The course to enroll in.
     */
    public void enroll(Course course) {
        Database database = new Database();
        database.enrollStudent(this, course);
        database.close();
    }
    
    /**
     * Enrolls the student in a lab.
     * @param lab The lab to enroll in.
     */
    public void enrolllab(Lab lab) {
        Database database = new Database();
        database.enrollStudent(this, lab);
        database.close();
    }

    /**
     * Views the grades of the student.
     */

    public void viewGrades() {
        Database database = new Database();
        database.viewGrades(this);
        database.close();
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
    public String getInstructor() {
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

