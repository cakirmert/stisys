package de.haw.hamburg.stisys.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import de.haw.hamburg.stisys.core.ControlledObject;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

@Controller
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private ControlledObject userService;

    @PostMapping("/login")
    public RedirectView login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        Map<String, Object> roleAndId = userService.authenticateUser(username, password);

        if (roleAndId != null) {
            String role = String.valueOf(roleAndId.get("role"));
            String userId = String.valueOf(roleAndId.get("userId"));

            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            session.setAttribute("role", role);

            switch (role) {
                case "FSB":
                    return new RedirectView("/fsb");
                case "Professor":
                    return new RedirectView("/professor");
                case "Student":
                    return new RedirectView("/student");
                default:
                    return new RedirectView("/error?errorMessage=Unknown role");
            }
        } else {
            return new RedirectView("/error");
        }
    }

    @GetMapping("/error")
    public String errorPage(Model model, @RequestParam(required = false) String errorMessage) {
        model.addAttribute("errorMessage", errorMessage);
        return "error-page";
    }
}
