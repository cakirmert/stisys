package de.haw.hamburg.stisys.core;

/**
 * The SystemFactory class provides factory methods for creating various objects in the system.
 * It implements the Factory Method design pattern.
 */
class SystemFactory {
    
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
}
