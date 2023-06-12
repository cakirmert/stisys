package de.haw.hamburg.stisys.core;

import java.util.Map;

/**
 * The SystemFactory class provides factory methods for creating various objects in the system.
 * It implements the Factory Method design pattern.
 */
class SystemFactory implements ControlledObject{
    
    /**
     * Creates a new Course object.
     * @param courseName The name of the course.
     * @param credits The number of credits for the course.
     * @param professor The professor teaching the course.
     * @return The created Course object.
     */
    public static Course createCourse(String courseName, int credits, Professor professor) {
        return new Course(courseName, credits, professor);
    }
    
    /**
     * Creates a new Lab object.
     * @param labName The name of the lab.
     * @param credits The number of credits for the lab.
     * @param professor The professor teaching the lab.
     * @param course The course associated with the lab.
     * @return The created Lab object.
     */
    public static Lab createLab(String labName, int credits, Professor professor, Course course) {
        Lab lab = new Lab(labName, credits, professor, course);
        course.setLab(lab);
        return lab;
    }
    
    /**
     * Creates a new Student object.
     * @param name The name of the student.
     * @param password The password of the student.
     * @return The created Student object.
     */
    public static Student createStudent(String name, String password) {
        return new Student(name, password);
    }
    
    /**
     * Creates a new Professor object.
     * @param name The name of the professor.
     * @param password The password of the professor.
     * @return The created Professor object.
     */
    public static Professor createProfessor(String name, String password) {
        return new Professor(name, password);
    }
    
    /**
     * Creates a new Database object.
     * @return The created Database object.
     */
    public static Database createDatabase() {
        return new Database();
    }

    @Override
    public String getCourseName() {
        throw new UnsupportedOperationException("Unimplemented method 'getCourseName'");
    }

    @Override
    public int getCredits() {
        throw new UnsupportedOperationException("Unimplemented method 'getCredits'");
    }

    @Override
    public void displayCourseInfo() {
        throw new UnsupportedOperationException("Unimplemented method 'displayCourseInfo'");
    }

    @Override
    public int getCourseID() {
        throw new UnsupportedOperationException("Unimplemented method 'getCourseID'");
    }

    @Override
    public void setId(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
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
