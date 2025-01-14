package com.example.fruit_api.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {
    private String id;
    private String keycloakId;
    private String userName;
    private String email;
    private String fullName;
    private String role;
    private String org;
    private String phone;
    private Boolean active;
}
