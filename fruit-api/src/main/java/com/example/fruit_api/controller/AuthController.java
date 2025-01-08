package com.example.fruit_api.controller;

import com.example.fruit_api.models.User;
import com.example.fruit_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAdminUser(@Valid @RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        userService.registerAdminUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}