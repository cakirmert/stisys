package de.haw.hamburg.stisys.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import de.haw.hamburg.stisys.core.ControlledObject;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ControlledObject userService;

    @GetMapping
    public String professorPage(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");

        if ("Professor".equals(role)) {
            return "professor-page";
        }

        model.addAttribute("errorMessage", "Access denied. Insufficient privileges.");
        return "not-authorized-page";
    }
    
    @PostMapping("/api/pvl/{studentId}")
    public ResponseEntity<String> setPVL(@RequestParam int studentId,@RequestParam int courseid, @RequestParam boolean pvl) {

         userService.setPVL(studentId,courseid,pvl);
        
        return ResponseEntity.ok("PVL has been set for studentId: " + studentId);
    }
    
    @PostMapping("/api/grades/{studentId}")
    public ResponseEntity<String> setGrade(@PathVariable int studentId, @RequestParam int grade) {
        // Logic to set the grade for the specified studentId
        // You can access the studentId and grade values here and perform the necessary actions
        // Example code:
        // userService.setGrade(studentId, grade);
        
        return ResponseEntity.ok("Grade has been set for studentId: " + studentId);
    }
}
