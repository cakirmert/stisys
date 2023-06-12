package de.haw.hamburg.stisys.api;

import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Controller
@RequestMapping("/fsb")
public class FSBController {
    private final AuthController authController;

    public FSBController(AuthController authController) {
        this.authController = authController;
    }

    @GetMapping
public String fsbPage(HttpServletRequest request, Model model, HttpServletResponse response) {
    SecretKey secretKey = authController.getSecretKey();
    // Retrieve the token from the cookie
    String jwtToken = getJwtTokenFromRequest(request);

    try {
        if (jwtToken != null) {
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwtToken).getBody();
            String role = (String) claims.get("role");

            // Check if the role is "FSB"
            if ("FSB".equals(role)) {
                // Add any necessary data to the model
                return "fsb-page";
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("errorMessage", "Error occurred: " + e.getMessage());
        return "error-page";
    }

    // If the role is not "FSB" or the token is missing, return an error page
    model.addAttribute("errorMessage", "Access denied. Insufficient privileges.");
    return "not-authorized-page";
}


    private String getJwtTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}


