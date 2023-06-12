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

}
