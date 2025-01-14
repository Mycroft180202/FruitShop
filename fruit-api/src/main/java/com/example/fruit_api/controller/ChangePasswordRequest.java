package com.example.fruit_api.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ChangePasswordRequest {
    @NotBlank(message = "Password is required")
    private String password;
}