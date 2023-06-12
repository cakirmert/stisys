package de.haw.hamburg.stisys.core;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * The AccessControlProxy class acts as a proxy for accessing controlled objects.
 * It provides access control checks and additional functionality before allowing method execution.
 * It implements the Proxy design pattern.
 */
@Component
public class AccessControlProxy<T extends ControlledObject> {
    private final T target;
    private final LoggingSingleton logger;

    /**
     * Constructs an instance of AccessControlProxy.
     *
     * @param target The controlled object to be proxied.
     */
    private AccessControlProxy(T target) {
        this.target = target;
        this.logger = LoggingSingleton.getInstance();
    }

    /**
     * Factory method for creating an instance of AccessControlProxy.
     *
     * @param t The controlled object to be proxied.
     * @param <T>    The type of the controlled object.
     * @return The AccessControlProxy instance.
     */
    public static <T extends ControlledObject> AccessControlProxy<T> getInstance(T t) {
        return new AccessControlProxy<>(t);
    }

    /**
     * Returns the controlled object.
     *
     * @return The controlled object.
     */
    private T getcontrolledobject() {
        return target;
    }

    /**
     * Checks if access is allowed based on the required role.
     *
     * @param requiredRole The required role for access.
     * @return true if access is allowed, false otherwise.
     */
    private boolean isAccessAllowed(String requiredRole) {

        // Check the role of the current user against the required role
        // Return true if access is allowed, false otherwise

        if (requiredRole.equals("Professor")) {
            if (target instanceof Professor) {
                return true;
            }
        } else if (requiredRole.equals("Student")) {
            if (target instanceof Student) {
                return true;
            }
        }

        return false;
    }

    /**
     * Enrolls a student in a course.
     *
     * @param controlledCourse The course to enroll in.
     */
    public void enroll(AccessControlProxy<ControlledObject> controlledCourse) {
        if (isAccessAllowed("Student")) {
            Student student = (Student) target;
            student.enroll((Course) controlledCourse.getcontrolledobject());
            logger.logInfo("Enrolling student: " + student.getName() + " in course: " + controlledCourse.getcontrolledobject().getCourseName());
        } else {
            logger.logWarning("Access denied for enrollStudent operation.");
        }
    }

    /**
     * Sets the grades for a student in a course.
     *
     * @param controlledStudent The controlled student object.
     * @param controlledCourse  The controlled course object.
     * @param grade             The grade to be set.
     */
    public void setGrades(AccessControlProxy<ControlledObject> controlledStudent, AccessControlProxy<ControlledObject> controlledCourse, int grade) {
        if (isAccessAllowed("Professor")) {
            Student student = (Student) controlledStudent.target;
            Course course = (Course) controlledCourse.target;
            Database db = new Database();
            db.saveGrade(student, course, grade);
            logger.logInfo("Setting grade: " + grade + " for student: " + student.getName() + " in course: " + course.getCourseName());
        } else {
            logger.logWarning("Access denied for setGrades operation.");
        }
    }

    /**
     * Sets the PVL for a student in a lab.
     *
     * @param pvl               The PVL to be set.
     */

     public void setPVL(int id,int courseid, boolean b) {
        target.setPVL(id,courseid, b);
    }
    /**
     * Sets the course ID.
     *
     * @param course The course ID to be set.
     */
    public void setCourseId(int course) {
        target.setId(course);
    }

    /**
     * Sets the student ID.
     *
     * @param id The student ID to be set.
     */
    public void setUserId(int id) {
        target.setId(id);
    }

    /**
     * Displays the course information.
     *
     * @param controlledCourse The controlled course object.
     */
    public void displayCourseInfo(AccessControlProxy<ControlledObject> controlledCourse) {
        controlledCourse.target.displayCourseInfo();
    }

    /**
     * Saves the course.
     *
     * @param controlledCourse The controlled course object.
     * @return The saved course ID.
     */
    public int saveCourse(AccessControlProxy<ControlledObject> controlledCourse) {
        return ((Database) target).saveCourse((Course) controlledCourse.target);
    }

    /**
     * Saves the lab.
     *
     * @param controlledLab The controlled lab object.
     * @return The saved lab ID.
     */
    public int saveLab(AccessControlProxy<ControlledObject> controlledLab) {
        return ((Database) target).saveLab((Lab) controlledLab.target);
    }

    /**
     * Saves the student.
     *
     * @param controlledStudent The controlled student object.
     * @return The generated student ID.
     */
    public int saveStudent(AccessControlProxy<ControlledObject> controlledStudent) {
        return ((Database) target).saveStudent((Student) controlledStudent.target);
    }

    /**
     * Views the grades of the student.
     */
    public  List<Map<String, Object>>viewGrades(int studentId) {
        return ((Database) target).viewGrades(studentId);
    }

    /**
     * Closes the database connection.
     */
    public void close() {
        ((Database) target).close();
    }

    /**
     * Creates a new course.
     *
     * @param courseName  The name of the course.
     * @param professorname     The number of credits for the course.
     * @param controlledProfessor   The professor teaching the course.
     * @return 
     * @return The created course object.
     */
     public AccessControlProxy<ControlledObject> createCourse(String courseName, String professorname, int credits) {
        return getInstance(SystemFactory.createCourse(courseName, professorname, credits));
    }

    /**
     * Creates a new lab.
     *
     * @param labName    The name of the lab.
     * @param credits    The number of credits for the lab.
     * @param controlledInstructor  The professor teaching the lab.
     * @param controlledCourse     The course associated with the lab.
     * @return 
     * @return The created lab object, or null if the course is not provided.
     */
    public AccessControlProxy<ControlledObject> createLab(String labName, int credits, String instructor, AccessControlProxy<ControlledObject> controlledCourse) {
        if (controlledCourse != null && controlledCourse.getcontrolledobject() != null) {
            return getInstance(SystemFactory.createLab(labName, credits, instructor, (Course) controlledCourse.getcontrolledobject()));
        } else {
            return null;
        }
    }

    /**
     * Enrolls a student in a lab.
     *
     * @param controlledLab The controlled lab object.
     */
    public void enrolllab(AccessControlProxy<ControlledObject> controlledLab) {
        if (isAccessAllowed("Student")) {
            Student student = (Student) target;
            student.enrolllab((Lab) controlledLab.getcontrolledobject());
            logger.logInfo("Enrolling student: " + student.getName() + " in lab: " + controlledLab.getcontrolledobject().getLabName());
        } else {
            logger.logWarning("Access denied for enrolllab operation.");
        }
    }

    /**
     * Displays the course information for a lab.
     *
     * @param controlledLab The controlled lab object.
     */
    public void displayCourseInfolab(AccessControlProxy<ControlledObject> controlledLab) {
        controlledLab.target.displayCourseInfo();
    }

    public AccessControlProxy<ControlledObject> createStudent(String name, String password) {
        return getInstance(SystemFactory.createStudent(name, password));
    }

    public AccessControlProxy<ControlledObject> createProfessor(String name, String password) {
        return getInstance(SystemFactory.createProfessor(name, password));
    }

    public int saveProfessor(AccessControlProxy<ControlledObject> controlledprof) {
        return ((Database) target).saveProfessor((Professor) controlledprof.target);
    }

    public int getId() {
        return ((User) target).getId();
    }


}
