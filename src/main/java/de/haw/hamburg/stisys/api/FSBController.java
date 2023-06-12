package de.haw.hamburg.stisys.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/fsb")
public class FSBController {

    @GetMapping
    public String fsbPage(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if ("FSB".equals(role)) {
            return "fsb-page";
        }

        model.addAttribute("errorMessage", "Access denied. Insufficient privileges.");
        return "not-authorized-page";
    }
}
