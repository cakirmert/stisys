package de.haw.hamburg.stisys.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import de.haw.hamburg.stisys.core.AccessControlProxy;
import de.haw.hamburg.stisys.core.ControlledObject;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/fsb")
public class FSBController {

    private final AccessControlProxy<ControlledObject> controlledDatabase;

    @Autowired
    public FSBController(AccessControlProxy<ControlledObject> controlledDatabase) {
        this.controlledDatabase = controlledDatabase;
    }

    @GetMapping
    public String fsbPage(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if ("FSB".equals(role)) {
            return "fsb-page";
        }

        model.addAttribute("errorMessage", "Access denied. Insufficient privileges.");
        return "not-authorized-page";
    }

    @PostMapping("/api/createStudent")
    public ResponseEntity<Integer> createStudent(@RequestBody Map<String, Object> request) {
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
    @PostMapping("/api/createProfessor")
        public ResponseEntity<Integer> createProfessor(@RequestBody Map<String, Object> request) {
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
        
    }