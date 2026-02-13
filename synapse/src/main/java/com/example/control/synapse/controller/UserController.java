package com.example.control.synapse.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.control.synapse.dto.request.UserSignupRequest;
import com.example.control.synapse.models.User;
import com.example.control.synapse.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User signup(@RequestBody UserSignupRequest request) {
        return userService.signup(request);
    }
}
