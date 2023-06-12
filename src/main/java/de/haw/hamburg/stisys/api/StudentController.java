package de.haw.hamburg.stisys.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping
    public String studentPage() {
        return "student-page";
    }

    // Add other student-specific endpoints and methods here
}
