package com.jejutic.web_service.ui;

import com.jejutic.web_service.ui.data_objects.UserInfo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
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
    public String login(@Valid UserInfo userInfo, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/login";
        }
        return "redirect:/";
    }
}
