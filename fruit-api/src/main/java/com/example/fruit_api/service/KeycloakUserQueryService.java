package com.example.fruit_api.service;

import com.example.fruit_api.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface KeycloakUserQueryService {
    Page<UserDTO> getAllUsers(Pageable pageable);

    UserDTO getUserById(String userId);

    UserDTO getUserByUsername(String userName);

    Page<UserDTO> searchUsers(String fullName, Boolean active, Pageable pageable);
}
