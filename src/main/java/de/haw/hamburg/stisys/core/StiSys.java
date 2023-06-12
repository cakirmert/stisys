package de.haw.hamburg.stisys.core;

/**
 * The StiSys class represents the main class of the StiSys application.
 * It contains the main method to run the application.
 * Author: Mert Cakir
 * Version: 08.06.2023
 */
public class StiSys {
    public static void main(String[] args) {

// Create controlled database instance
AccessControlProxy<Database> controlledDatabase = AccessControlProxy.getInstance(SystemFactory.createDatabase());

// Create controlled professor and instructor instances
AccessControlProxy<Professor> controlledInstructor = AccessControlProxy.getInstance(SystemFactory.createProfessor("Jane Doe", "cleartext"));
AccessControlProxy<Professor> controlledProfessor = AccessControlProxy.getInstance(SystemFactory.createProfessor("John Doe", "cleartext"));

// Create controlled courses
AccessControlProxy<ControlledObject> controlledCourse = (controlledDatabase.createCourse("Software Engineering", 3, controlledProfessor));
AccessControlProxy<ControlledObject> controlledLab = (controlledDatabase.createLab("Software Engineering Lab", 3, controlledInstructor,controlledCourse));

controlledCourse.setCourseId(controlledDatabase.saveCourse(controlledCourse));
controlledLab.setCourseId(controlledDatabase.saveLab(controlledLab));

// Create controlled student instance
AccessControlProxy<ControlledObject> controlledStudent = AccessControlProxy.getInstance(SystemFactory.createStudent("Alice Johnson", "cleartext"));

// Save the student in the database
controlledStudent.setStudentId(controlledDatabase.saveStudent(controlledStudent));

// Enroll the student in the courses
controlledStudent.enroll(controlledCourse);
controlledStudent.enrolllab(controlledLab);

// Display course information
controlledLab.displayCourseInfolab(controlledLab);
controlledLab.displayCourseInfo(controlledCourse);

// Professor gives PVL and grade to the student in the lab course
controlledInstructor.setPVL(controlledStudent, controlledLab, true);
// Student views PVL Status
controlledStudent.viewGrades();

// Professor sets the grades for the student in the course
controlledProfessor.setGrades(controlledStudent, controlledCourse, 15);

// Student views grades after exam
controlledStudent.viewGrades();

// Close the controlled database connection
controlledDatabase.close();

}

}