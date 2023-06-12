package de.haw.hamburg.stisys.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import de.haw.hamburg.stisys.core.ControlledObject;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

@Controller
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private ControlledObject userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login-page";
    }

    @PostMapping("/login")
    public RedirectView login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Map<String, Object> roleAndId = userService.authenticateUser(username, password);

        if (roleAndId != null) {
            String role = String.valueOf(roleAndId.get("role"));
            String userId = String.valueOf(roleAndId.get("userId"));

            session.setAttribute("userId", userId);
            session.setAttribute("role", role);

            return new RedirectView("/api/home");
        } else {
            return new RedirectView("/error?errorMessage=Invalid credentials");
        }
    }

    @GetMapping("/home")
    public String homePage(HttpSession session, RedirectAttributes redirectAttributes) {
        String role = (String) session.getAttribute("role");
    
        if (role != null) {
            switch (role) {
                case "FSB":
                    return "redirect:/fsb";
                case "Professor":
                    return "redirect:/professor";
                case "Student":
                    return "redirect:/student";
                default:
                    redirectAttributes.addAttribute("errorMessage", "Unknown role");
                    return "redirect:/error";
            }
        } else {
            redirectAttributes.addAttribute("errorMessage", "Not logged in");
            return "redirect:/error";
        }
    }
    
}
