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



}

