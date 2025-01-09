package com.example.fruit_api.controller;

import com.example.fruit_api.dto.ApiResponse;
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

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(new ApiResponse("Login successful!", true));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("Invalid username or password", false));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerAdminUser(@Valid @RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body(new ApiResponse("Error: Username is already taken!", false));
        }

        userService.registerAdminUser(user);
        return ResponseEntity.ok(new ApiResponse("User registered successfully!", true));
    }
}