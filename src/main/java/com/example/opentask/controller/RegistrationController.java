package com.example.opentask.controller;

import com.example.opentask.domain.User;
import com.example.opentask.repos.UserRepository;
import com.example.opentask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    public RegistrationController() {
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());


        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        userService.save(user);

        return "redirect:/login";
    }
}

