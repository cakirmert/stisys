package de.haw.hamburg.stisys.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @GetMapping
    public String professorPage() {
        return "professor-page";
    }

    // Add other professor-specific endpoints and methods here
}
