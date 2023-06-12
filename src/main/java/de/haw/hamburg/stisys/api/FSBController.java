package de.haw.hamburg.stisys.api;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import de.haw.hamburg.stisys.core.AccessControlProxy;
import de.haw.hamburg.stisys.core.ControlledObject;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/fsb")
public class FSBController {

    private final AccessControlProxy<ControlledObject> controlledDatabase;

    @Autowired
    public FSBController(AccessControlProxy<ControlledObject> controlledDatabase) {
        this.controlledDatabase = controlledDatabase;
    }

    @GetMapping
    public String fsbPage(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");

        if ("FSB".equals(role)) {
            return "fsb-page";
        }

        model.addAttribute("errorMessage", "Access denied. Insufficient privileges.");
        return "not-authorized-page";
    }

    @PostMapping("/api/createStudent")
    public ResponseEntity<Integer> createStudent(HttpSession session, @RequestBody Map<String, Object> request) {
        // Retrieve the role from the session
        String role = (String) session.getAttribute("role");
    
        // Check if the role is FSB
        if ("FSB".equals(role)) {
            // Proceed with creating the student
            String name = request.get("name").toString();
            String password = request.get("password").toString();
    
            // Create a controlled student object
            AccessControlProxy<ControlledObject> controlledStudent = controlledDatabase.createStudent(name, password);
            // Save the controlled student in the database and retrieve the student ID
            int studentId = controlledDatabase.saveStudent(controlledStudent);
            controlledStudent.setUserId(studentId);
    
            // Return the student ID as the response
            return ResponseEntity.ok().body(studentId);
        }
    
        // If the role is not FSB, return an error response
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    
    @PostMapping("/api/createProfessor")
    public ResponseEntity<Integer> createProfessor(HttpSession session, @RequestBody Map<String, Object> request) {
        // Retrieve the role from the session
        String role = (String) session.getAttribute("role");
    
        // Check if the role is FSB
        if ("FSB".equals(role)) {
            // Proceed with creating the professor
            String name = request.get("name").toString();
            String password = request.get("password").toString();
    
            // Create a controlled professor object
            AccessControlProxy<ControlledObject> controlledProfessor = controlledDatabase.createProfessor(name, password);
            // Save the controlled professor in the database and retrieve the professor ID
            int professorId = controlledDatabase.saveProfessor(controlledProfessor);
            controlledProfessor.setUserId(professorId);
    
            // Return the professor ID as the response
            return ResponseEntity.ok().body(professorId);
        }
    
        // If the role is not FSB, return an error response
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    
    @PostMapping("/api/courses")
    public ResponseEntity<String> addCourse(HttpSession session, @RequestBody Map<String, Object> request) {
        // Retrieve the role from the session
        String role = (String) session.getAttribute("role");
    
        // Check if the role is FSB
        if ("FSB".equals(role)) {
            // Retrieve the course details from the request
            String courseName = request.get("courseName").toString();
            String professorname = request.get("professorName").toString();
            int credits = Integer.parseInt(request.get("credits").toString());
    
            // Create a controlled course object
            AccessControlProxy<ControlledObject> controlledCourse = controlledDatabase.createCourse(courseName, professorname, credits);
    
            // Save the controlled course in the database and retrieve the course ID
            int courseId = controlledDatabase.saveCourse(controlledCourse);
            controlledCourse.setCourseId(courseId);
    
            // Return a success response with the course ID
            return ResponseEntity.ok().body("Course added successfully with ID: " + courseId);
        }
    
        // If the role is not FSB, return an error response
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    
    }