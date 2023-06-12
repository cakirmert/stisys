package de.haw.hamburg.stisys.core;

/**
 * The User class represents a user in the system.
 * It serves as a base class for Student and Professor.
 */
abstract class User implements ControlledObject {
    private final String name;
    private int id;
    private final String password;
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

}
