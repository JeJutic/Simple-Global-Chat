package com.jejutic.social.ui;

import com.jejutic.social.ui.data_objects.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@SessionAttributes("userInfo")
public class LoginPage {

    @ModelAttribute("userInfo")
    public UserInfo userInfo() {
        return new UserInfo();
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String login(UserInfo userInfo) {
        return "redirect:/";
    }
}
