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
    
    @PostMapping("/api/pvl")
    public ResponseEntity<String> setPVL(@RequestParam int studentId, @RequestParam int courseId, @RequestParam boolean pvl) {
        userService.setPVL(studentId, courseId, pvl);
        
        return ResponseEntity.ok("PVL has been set for studentId: " + studentId);
    }
    
    @PostMapping("/api/grades")
    public ResponseEntity<String> setGrade(@RequestParam int studentId, @RequestParam int courseId, @RequestParam int grade) {
        userService.setGrade(studentId, courseId, grade);

        return ResponseEntity.ok("Grade has been set for studentId: " + studentId);
    }
}
