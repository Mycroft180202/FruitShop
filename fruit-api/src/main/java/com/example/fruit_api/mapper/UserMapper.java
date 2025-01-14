package com.example.fruit_api.mapper;

import com.example.fruit_api.dto.UserCreateDTO;
import com.example.fruit_api.dto.UserDTO;
import com.example.fruit_api.dto.UserUpdateDTO;
import com.example.fruit_api.models.User;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapper {
    // Chuyển đổi từ UserCreateDTO sang User entity
    public static User toEntity(UserCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setRole(dto.getRole());
        user.setOrg(dto.getOrg());
        user.setPhone(dto.getPhone());
        user.setActive(dto.getActive());

        return user;
    }

    // Chuyển đổi từ UserUpdateDTO sang User entity (cập nhật)
    public static void updateEntityFromDTO(UserUpdateDTO dto, User user) {
        if (dto == null || user == null) {
            return;
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getFullName() != null) {
            user.setFullName(dto.getFullName());
        }
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }
        if (dto.getOrg() != null) {
            user.setOrg(dto.getOrg());
        }
        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getActive() != null) {
            user.setActive(dto.getActive());
        }
    }

    // Chuyển đổi từ User entity sang UserDTO
    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(user.getId().toString());
        dto.setKeycloakId(user.getKeycloakId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setRole(user.getRole());
        dto.setOrg(user.getOrg());
        dto.setPhone(user.getPhone());
        dto.setActive(user.getActive());

        return dto;
    }

    // Chuyển đổi từ User entity sang UserRepresentation (Keycloak)
    public static UserRepresentation toKeycloakUser(User user) {
        if (user == null) {
            return null;
        }
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(user.getActive());
        userRepresentation.setUsername(user.getUserName());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setFirstName(extractFirstName(user.getFullName()));
        userRepresentation.setLastName(extractLastName(user.getFullName()));
        userRepresentation.setAttributes(createUserAttributes(user));

        return userRepresentation;
    }

    // Tạo attributes cho UserRepresentation
    private static Map<String, List<String>> createUserAttributes(User user) {
        Map<String, List<String>> attributes = new HashMap<>();
        if (user.getPhone() != null && !user.getPhone().isEmpty()) {
            attributes.put("phone", Collections.singletonList(user.getPhone()));
        }
        return attributes;
    }

    // Trích xuất first name từ full name
    public static String extractFirstName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "";
        }
        String[] parts = fullName.split("\\s+", 2);
        return parts[0];
    }

    // Trích xuất last name từ full name
    public static String extractLastName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "";
        }
        String[] parts = fullName.split("\\s+", 2);
        return parts.length > 1 ? parts[1] : "";
    }
}
