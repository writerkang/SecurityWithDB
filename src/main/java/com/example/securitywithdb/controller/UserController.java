package com.example.securitywithdb.controller;

import com.example.securitywithdb.command.RegisterUserCommand;
import com.example.securitywithdb.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup() {

        return "signup";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/logout")
    public String logout() {

        return "logout";
    }

    @PostMapping("/signup")
    public String registerUser(RegisterUserCommand command) {
        log.info(command.toString());
        userService.registerUser(command);

        return "redirect:/login";
    }
}
