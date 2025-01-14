package com.example.fruit_api.service;

import com.example.fruit_api.dto.UserCreateDTO;
import com.example.fruit_api.dto.UserDTO;
import com.example.fruit_api.dto.UserUpdateDTO;
import com.example.fruit_api.models.User;
import com.example.fruit_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface KeycloakUserCommandService {
    UserDTO createUser(UserCreateDTO userCreateDTO);

    UserDTO updateUser(String userId, UserUpdateDTO userUpdateDTO);

    UserDTO enableUser(String userId);

    UserDTO disableUser(String userId);

    void deleteUser(String userId);

    void changeUserPassword(String userId, String password);
}