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
}
