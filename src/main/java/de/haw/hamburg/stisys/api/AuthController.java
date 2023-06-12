package de.haw.hamburg.stisys.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import de.haw.hamburg.stisys.core.ControlledObject;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

@Controller
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private ControlledObject userService;

    @PostMapping("/login")
    public RedirectView login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        Map<String, Object> roleAndId = userService.authenticateUser(username, password);

        if (roleAndId != null) {
            String role = String.valueOf(roleAndId.get("role"));
            String userId = String.valueOf(roleAndId.get("userId"));

            Cookie userCookie = new Cookie("userId", userId);
            userCookie.setMaxAge(30 * 60);
            userCookie.setHttpOnly(true);
            userCookie.setSecure(true);
            response.addCookie(userCookie);

            Cookie roleCookie = new Cookie("role", role);
            roleCookie.setMaxAge(30 * 60);
            roleCookie.setHttpOnly(true);
            roleCookie.setSecure(true);
            response.addCookie(roleCookie);

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
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return new RedirectView("/error");
        }
    }

    @GetMapping("/error")
    public String errorPage(Model model, @RequestParam(required = false) String errorMessage) {
        model.addAttribute("errorMessage", errorMessage);
        return "error-page";
    }
}
