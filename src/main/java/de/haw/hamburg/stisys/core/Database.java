package de.haw.hamburg.stisys.core;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * The Database class represents a controlled object that implements the ControlledObject interface.
 */
@Service
class Database implements ControlledObject {
    
    private Connection connection;

    public Database() {
        // Initialize the database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stisys", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> authenticateUser(String username, String password) {
        String sql = "SELECT role, id FROM user WHERE username = ? AND password = ?";
    
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
    
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Map<String, Object> result = new HashMap<>();
                result.put("role", rs.getString("role"));
                result.put("userId", String.valueOf(rs.getInt("id"))); // Store as String
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return null; // Return null if authentication fails or user not found
    }
    

    /**
     * Saves a student to the database.
     * @param student The student to be saved.
     * @return The generated student ID.
     */
    public int saveStudent(Student student) {
        String sql = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";
    
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getPassword());
            pstmt.setString(3, "Student"); // Set the role to "Student"
            pstmt.executeUpdate();
    
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return -1;
    }
    

    /**
     * Enrolls a student in a course or lab.
     * @param student The student to enroll.
     * @param course The course or lab to enroll in.
     */
    public void enrollStudent(Student student, Course course) {
        if (course instanceof Lab) {
            Lab lab = (Lab) course;
    
            // Check if the lab or course already exists in the results table for the student
            String checkResultSql = "SELECT * FROM results WHERE student_id = ? AND (course_id = ? OR lab_id = ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(checkResultSql)) {
                pstmt.setInt(1, student.getId());
                pstmt.setInt(2, lab.getCourse().getCourseID());
                pstmt.setInt(3, lab.getLabID());
                ResultSet rs = pstmt.executeQuery();
    
                if (rs.next()) {
                    // Entry exists, perform an update
                    String updateResultSql = "UPDATE results SET lab_id = ? WHERE student_id = ? AND (course_id = ? OR lab_id = ?)";
                    try (PreparedStatement updatePstmt = connection.prepareStatement(updateResultSql)) {
                        updatePstmt.setInt(1, lab.getLabID());
                        updatePstmt.setInt(2, student.getId());
                        updatePstmt.setInt(3, lab.getCourse().getCourseID());
                        updatePstmt.setInt(4, lab.getLabID());
                        updatePstmt.executeUpdate();
                    }
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
    
            // Enroll student in the lab
            String enrollLabSql = "INSERT INTO results (student_id, course_id, lab_id) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(enrollLabSql)) {
                pstmt.setInt(1, student.getId());
                pstmt.setInt(2, lab.getCourse().getCourseID());
                pstmt.setInt(3, lab.getLabID());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Check if the course already exists in the results table for the student
            String checkCourseSql = "SELECT * FROM results WHERE student_id = ? AND course_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(checkCourseSql)) {
                pstmt.setInt(1, student.getId());
                pstmt.setInt(2, course.getCourseID());
                ResultSet rs = pstmt.executeQuery();
    
                if (rs.next()) {
                    System.out.println("Student is already enrolled in Course with ID " + course.getCourseID());
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
    
            // Enroll student in the regular course
            String enrollCourseSql = "INSERT INTO results (student_id, course_id) VALUES (?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(enrollCourseSql)) {
                pstmt.setInt(1, student.getId());
                pstmt.setInt(2, course.getCourseID());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Displays information about a course.
     * @param courseId The ID of the course.
     */
    public void displayCourseInfo(int courseId) {
        String sql = "SELECT c.course_name, c.credits, c.professor_name, l.lab_name FROM course c LEFT JOIN lab l ON c.id = l.course_id WHERE c.id = ?";
    
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                String courseName = rs.getString("course_name");
                int credits = rs.getInt("credits");
                String professorName = rs.getString("professor_name");
                String labName = rs.getString("lab_name");
    
                System.out.println("Course Name: " + courseName);
                System.out.println("Course ID: " + courseId);
                System.out.println("Credits: " + credits);
                System.out.println("Professor: " + professorName);
                if (labName != null) {
                    System.out.println("Lab: " + labName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a course to the database.
     * @param course The course to be saved.
     * @return The generated course ID.
     */
    public int saveCourse(Course course) {
        String sql = "INSERT INTO course (course_name, credits, professor_name) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, course.getCredits());
            pstmt.setString(3, course.getProfessor());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Saves a lab to the database.
     * @param lab The lab to be saved.
     * @return The generated lab ID.
     */
    public int saveLab(Lab lab) {
        String sql = "INSERT INTO lab (course_id, professor_name, lab_name) VALUES (?, ?, ?)";
    
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, lab.getCourse().getCourseID());
            pstmt.setString(2, lab.getInstructor());
            pstmt.setString(3, lab.getLabName());
            pstmt.executeUpdate();
    
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int labId = rs.getInt(1);
                lab.setLabID(labId);
                return labId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return -1;
    }

    /**
     * Adds a lab to a course.
     * @param courseId The ID of the course.
     * @param labId The ID of the lab.
     */
    public void addLabToCourse(int courseId, int labId) {
        String sql = "UPDATE lab SET course_id = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, labId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setGrade(int studentId, int courseId, int grade) {
        String selectSql = "SELECT * FROM results WHERE student_id = ? AND course_id = ?";
    
        try (PreparedStatement selectPstmt = connection.prepareStatement(selectSql)) {
            selectPstmt.setInt(1, studentId);
            selectPstmt.setInt(2, courseId);
            ResultSet rs = selectPstmt.executeQuery();
    
            if (rs.next()) {
                // Entry exists, perform an update
                String updateSql = "UPDATE results SET grade = ? WHERE student_id = ? AND course_id = ?";
    
                try (PreparedStatement updatePstmt = connection.prepareStatement(updateSql)) {
                    updatePstmt.setInt(1, grade);
                    updatePstmt.setInt(2, studentId);
                    updatePstmt.setInt(3, courseId);
                    updatePstmt.executeUpdate();
                }
            } else {
                // Entry doesn't exist, perform an insert
                String insertSql = "INSERT INTO results (student_id, course_id, grade) VALUES (?, ?, ?)";
    
                try (PreparedStatement insertPstmt = connection.prepareStatement(insertSql)) {
                    insertPstmt.setInt(1, studentId);
                    insertPstmt.setInt(2, courseId);
                    insertPstmt.setInt(3, grade);
                    insertPstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    

    public List<Map<String, Object>> viewGrades(int studentId) {
        String sql = "SELECT results.course_id, results.grade, results.pvl, course.course_name " +
                     "FROM results " +
                     "INNER JOIN course ON results.course_id = course.id " +
                     "LEFT JOIN lab ON results.lab_id = lab.id " +
                     "WHERE results.student_id = ?";
    
        List<Map<String, Object>> gradesList = new ArrayList<>();
    
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                int grade = rs.getInt("grade");
                int labPVL = rs.getInt("pvl");
                String courseName = rs.getString("course_name");
    
                Map<String, Object> gradeData = new HashMap<>();
                gradeData.put("courseName", courseName);
                gradeData.put("courseId", courseId);
                gradeData.put("grade", grade);
                gradeData.put("labPVL", getPVLStatus(labPVL));
    
                gradesList.add(gradeData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return gradesList;
    }
    
    
    /**
     * Gets the PVL status as a string representation.
     * @param pvl The PVL value.
     * @return The PVL status.
     */
    private String getPVLStatus(int pvl) {
        switch (pvl) {
            case 0:
                return "Fail";
            case 1:
                return "Pass";
            default:
                return "Unknown";
        }
    }
    
    /**
     * Retrieves the database connection.
     * @return The database connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Closes the database connection.
     */
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implementing ControlledObject interface methods

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

    public void setGrades(Student student, Course course, int grade) {

    }

    public void setId(int id) {

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
    public String getInstructor() {
        throw new UnsupportedOperationException("Unimplemented method 'getInstructor'");
    }

    @Override
    public boolean hasLabPVL() {
        throw new UnsupportedOperationException("Unimplemented method 'hasLabPVL'");
    }

    @Override
    public void enroll(Course course) {
        throw new UnsupportedOperationException("Unimplemented method 'enroll'");
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

    public int saveProfessor(Professor professor) {
        String sql = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";
    
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, professor.getName());
            pstmt.setString(2, professor.getPassword());
            pstmt.setString(3, "Professor"); // Set the role to "Professor"
            pstmt.executeUpdate();
    
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return -1;
    }

    @Override
    public void setPVL(int studentId,int courseid, boolean pvl) {
        String selectSql = "SELECT * FROM results WHERE student_id = ? AND course_id = ?";
    
        try (PreparedStatement selectPstmt = connection.prepareStatement(selectSql)) {
            selectPstmt.setInt(1, studentId);
            selectPstmt.setInt(2, courseid);
            ResultSet rs = selectPstmt.executeQuery();
    
            int pvlValue = pvl ? 1 : 0;
            if (rs.next()) {
                // Entry exists, perform an update
                String updateSql = "UPDATE results SET pvl = ? WHERE student_id = ? AND course_id = ?";
    
                try (PreparedStatement updatePstmt = connection.prepareStatement(updateSql)) {
                    updatePstmt.setInt(1, pvlValue);
                    updatePstmt.setInt(2, studentId);
                    updatePstmt.setInt(3, courseid);
                    updatePstmt.executeUpdate();
                }
            } else {
                // Entry doesn't exist, perform an insert
                String insertSql = "INSERT INTO results (student_id, course_id, pvl) VALUES (?, ?, ?)";
    
                try (PreparedStatement insertPstmt = connection.prepareStatement(insertSql)) {
                    insertPstmt.setInt(1, studentId);
                    insertPstmt.setInt(2, courseid);
                    insertPstmt.setInt(3, pvlValue);
                    insertPstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
