package com.example.fruit_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateDTO {
    @NotBlank(message = "Username is required")
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String fullName;

    private String role;

    private String org;

    private String phone;

    private Boolean active = true;

}
