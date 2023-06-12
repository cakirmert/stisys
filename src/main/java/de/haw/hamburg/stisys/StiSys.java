package de.haw.hamburg.stisys;

import de.haw.hamburg.stisys.core.AccessControlProxy;
import de.haw.hamburg.stisys.core.ControlledObject;

/**
 * The StiSys class represents the main class of the StiSys application.
 * It contains the main method to run the application.
 * Author: Mert Cakir
 * Version: 08.06.2023
 */
public class StiSys {
    public static void main(String[] args) {

// Create controlled database instance
AccessControlProxy<ControlledObject> controlledDatabase = ControlledObject.createDummyObject();

// Create controlled professor and instructor instances
AccessControlProxy<ControlledObject> controlledInstructor = (controlledDatabase.createProfessor("Jane Doe", "cleartext"));
AccessControlProxy<ControlledObject> controlledProfessor = (controlledDatabase.createProfessor("John Doe", "cleartext"));
controlledInstructor.setUserId(controlledDatabase.saveProfessor(controlledInstructor));
controlledProfessor.setUserId(controlledDatabase.saveProfessor(controlledProfessor));

// Create controlled courses
AccessControlProxy<ControlledObject> controlledCourse = (controlledDatabase.createCourse("Software Engineering", "John Doe", 4));
AccessControlProxy<ControlledObject> controlledLab = (controlledDatabase.createLab("Software Engineering Lab", 3, "Jane Doe",controlledCourse));

controlledCourse.setCourseId(controlledDatabase.saveCourse(controlledCourse));
controlledLab.setCourseId(controlledDatabase.saveLab(controlledLab));

// Create controlled student instance
AccessControlProxy<ControlledObject> controlledStudent = (controlledDatabase.createStudent("Alice Johnson", "cleartext"));

// Save the student in the database
controlledStudent.setUserId(controlledDatabase.saveStudent(controlledStudent));

// Enroll the student in the courses
controlledStudent.enroll(controlledCourse);
controlledStudent.enrolllab(controlledLab);

// Display course information
controlledLab.displayCourseInfolab(controlledLab);
controlledLab.displayCourseInfo(controlledCourse);

// Professor gives PVL and grade to the student in the lab course


// Professor sets the grades for the student in the course



// Close the controlled database connection
controlledDatabase.close();

}

}