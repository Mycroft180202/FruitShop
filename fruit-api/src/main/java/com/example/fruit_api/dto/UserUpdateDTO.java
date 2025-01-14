package com.example.fruit_api.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserUpdateDTO {
    @Email(message = "Invalid email format")
    private String email;

    private String fullName;

    private String role;

    private String org;

    private String phone;

    private Boolean active;
}