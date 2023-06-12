package de.haw.hamburg.stisys.core;

import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * The ControlledObject interface defines the operations that controlled objects must implement.
 * It is used as a common interface for different controlled objects.
 */
@Component
public interface ControlledObject {
    String getCourseName();
    int getCredits();
    void displayCourseInfo();
    int getCourseID();
    void setId(int id);
    Map<String, Object> authenticateUser(String username, String password);
    void setGrades(Student student, Course course, int grade);
    void enrolllab(Lab lab);

    // Additional methods for Course
    String getProfessor();
    void setLab(Lab lab);

    // Additional methods for Lab
    Course getCourse();
    int getLabID();
    String getLabName();
    String getInstructor();
    boolean hasLabPVL();
    void setPVL(Student student, Lab lab, boolean pvl);

    // Additional methods for Student
    void enroll(Course course);
    void viewGrades();

    // Additional methods for Professor
    void createCourseFSB(Course course);
    void viewList(Course course);
    void viewCourses(Course course);
    public static AccessControlProxy<ControlledObject> createDummyObject() {
        return (AccessControlProxy.getInstance(SystemFactory.createDatabase())) ;
    }
}


