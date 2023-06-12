package de.haw.hamburg.stisys.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {

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

    // Add other student-specific endpoints and methods here
}
