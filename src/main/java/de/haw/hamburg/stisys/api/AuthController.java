package de.haw.hamburg.stisys.api;

import java.util.Date;
import java.util.Map;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import de.haw.hamburg.stisys.core.ControlledObject;

@Controller
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private ControlledObject userService;
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @PostMapping("/login")
public String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) throws IOException {
    // Check the user credentials against the database
    Map<String, Object> roleandid = userService.authenticateUser(username, password);

    if (roleandid != null) {
        String jwtToken = Jwts.builder()
            .setSubject(username)
            .claim("role", roleandid)
            .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // Set token to expire in 30 minutes
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();

        // Create a cookie containing the JWT token
        Cookie jwtCookie = new Cookie("jwtToken", jwtToken);
        jwtCookie.setMaxAge(30 * 60); // Set the cookie's expiration time to match the token's expiration
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);

        // Add the cookie to the response
        response.addCookie(jwtCookie);

        // Set the response status code
        response.setStatus(HttpServletResponse.SC_OK);

        // Return the view name based on the user's role
        String role = (String) roleandid.get("role");
        switch (role) {
            case "FSB":
                return "redirect:/fsb?id=" + roleandid.get("userId");
            case "Professor":
                return "redirect:/professor?id=" + roleandid.get("userId");
            case "Student":
                return "redirect:/student?id=" + roleandid.get("userId");
            default:
                return "redirect:/error?errorMessage=Unknown role";
        }
    } else {
        // Set the response status code and error message
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid username or password");

        // Return the error page view name
        return "error-page";
    }
}


    @GetMapping("/error")
    public String errorPage(Model model, @RequestParam(required = false) String errorMessage) {
        model.addAttribute("errorMessage", errorMessage);
        return "error-page";
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}

