package de.haw.hamburg.stisys.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/fsb")
public class FSBController {

    @GetMapping
    public String fsbPage(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userInfo".equals(cookie.getName())) {
                    String userInfo = cookie.getValue();
                    String[] infoArr = userInfo.split("\\|");
                    if (infoArr.length == 2) {
                        String userId = infoArr[0];
                        String role = infoArr[1];
    
                        if ("FSB".equals(role)) {
                            return "fsb-page";
                        }
                    }
                }
            }
        }

        model.addAttribute("errorMessage", "Access denied. Insufficient privileges.");
        return "not-authorized-page";
    }
}
