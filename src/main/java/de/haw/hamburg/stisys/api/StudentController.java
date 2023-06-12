package de.haw.hamburg.stisys.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import de.haw.hamburg.stisys.core.ControlledObject;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private ControlledObject userService;
    @GetMapping
    public String studentPage(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if ("Student".equals(role)) {
            return "student-page";
        } else {
            model.addAttribute("errorMessage", "Access denied. Insufficient privileges.");
            return "not-authorized-page";
        }
    }

    @PostMapping("/api/grades")
    public ResponseEntity<List<Map<String, Object>>> getGrades(HttpSession session, @RequestBody Map<String, Integer> request) {
        int studentId = Integer.parseInt(String.valueOf(session.getAttribute("userId")));
        List<Map<String, Object>> grades = userService.viewGrades(studentId);

        return ResponseEntity.ok().body(grades);
    }
    
}
