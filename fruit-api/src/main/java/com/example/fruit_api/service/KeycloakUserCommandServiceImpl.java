package com.example.fruit_api.service;

import com.example.fruit_api.dto.UserCreateDTO;
import com.example.fruit_api.dto.UserDTO;
import com.example.fruit_api.dto.UserUpdateDTO;
import com.example.fruit_api.common.api.exception.DuplicateUserException;
import com.example.fruit_api.common.api.exception.UserNotFoundException;
import com.example.fruit_api.mapper.UserMapper;
import com.example.fruit_api.models.User;
import com.example.fruit_api.repository.UserRepository;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakUserCommandServiceImpl implements  KeycloakUserCommandService {
    private final Keycloak keycloak;
    private final UserRepository userRepository;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${default-password}")
    private String defaultPassword;

    @Override
    @Transactional
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        // Kiểm tra sự tồn tại của user thông qua username hoặc email
        if (userRepository.existsByUserName(userCreateDTO.getUserName())) {
            throw new DuplicateUserException("Username already exists: " + userCreateDTO.getUserName());
        }
        if (userRepository.existsByEmail(userCreateDTO.getEmail())) {
            throw new DuplicateUserException("Email already exists: " + userCreateDTO.getEmail());
        }

        // Tạo và lưu user vào database trước
        User user = UserMapper.toEntity(userCreateDTO);
        user = userRepository.save(user);

        // Tạo UserRepresentation
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUserName());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setFirstName(UserMapper.extractFirstName(user.getFullName()));
        userRepresentation.setLastName(UserMapper.extractLastName(user.getFullName()));
        userRepresentation.setEnabled(true);

        // Set password mặc định và yêu cầu đổi password
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(defaultPassword);
        credentialRepresentation.setTemporary(true); // Yêu cầu đổi password trong lần đăng nhập đầu tiên
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

        // Required actions: yêu cầu user cập nhật thông tin sau khi đăng nhập
        userRepresentation.setRequiredActions(Collections.singletonList("UPDATE_PASSWORD"));

        // Tạo user trong Keycloak
        UsersResource usersResource = keycloak.realm(realm).users();
        Response response = usersResource.create(userRepresentation);

        // Xử lý kết quả từ Keycloak
        if (response.getStatus() == 201) {
            String userId = CreatedResponseUtil.getCreatedId(response);
            // Cập nhật ID và keycloakId của user trong database
            user.setKeycloakId(userId);
            userRepository.save(user);
            return UserMapper.toDTO(user);
        } else {
            // Nếu tạo user trên Keycloak không thành công, xóa user khỏi database
            userRepository.delete(user);
            throw new RuntimeException("Failed to create user: " + response.getStatusInfo());
        }
    }

    @Override
    @Transactional
    public UserDTO updateUser(String userId, UserUpdateDTO userUpdateDTO) {
        // Cập nhật thông tin user trong database trước
        User user = userRepository.findByKeycloakId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        UserMapper.updateEntityFromDTO(userUpdateDTO, user);
        user = userRepository.save(user);

        // Cập nhật thông tin user trong Keycloak
        try {
            UserRepresentation userRepresentation = keycloak.realm(realm).users().get(userId).toRepresentation();
            // Chỉ cập nhật các trường có sẵn trên Keycloak
            userRepresentation.setEmail(user.getEmail());
            userRepresentation.setEnabled(user.getActive());
            userRepresentation.setFirstName(UserMapper.extractFirstName(user.getFullName()));
            userRepresentation.setLastName(UserMapper.extractLastName(user.getFullName()));
            keycloak.realm(realm).users().get(userId).update(userRepresentation);
            return UserMapper.toDTO(user);
        } catch (Exception e) {
            log.error("Failed to update user in Keycloak. User ID: {}", userId, e);
            throw new RuntimeException("Failed to update user in Keycloak", e);
        }
    }

    @Override
    @Transactional
    public UserDTO enableUser(String userId) {
        // Cập nhật trạng thái user trong database trước
        User user = userRepository.findByKeycloakId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        user.setActive(true);
        user = userRepository.save(user);

        try {
            UserRepresentation userRepresentation = keycloak.realm(realm).users().get(userId).toRepresentation();
            userRepresentation.setEnabled(true);
            keycloak.realm(realm).users().get(userId).update(userRepresentation);
            return UserMapper.toDTO(user);
        } catch (Exception e) {
            log.error("Failed to enable user in Keycloak. User ID: {}", userId, e);
            throw new RuntimeException("Failed to enable user in Keycloak", e);
        }
    }

    @Override
    @Transactional
    public UserDTO disableUser(String userId) {
        // Cập nhật trạng thái user trong database trước
        User user = userRepository.findByKeycloakId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        user.setActive(false);
        user = userRepository.save(user);

        try {
            UserRepresentation userRepresentation = keycloak.realm(realm).users().get(userId).toRepresentation();
            userRepresentation.setEnabled(false);
            keycloak.realm(realm).users().get(userId).update(userRepresentation);
            return UserMapper.toDTO(user);
        } catch (Exception e) {
            log.error("Failed to disable user in Keycloak. User ID: {}", userId, e);
            throw new RuntimeException("Failed to disable user in Keycloak", e);
        }
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
        // Xóa user khỏi database trước
        User user = userRepository.findByKeycloakId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        userRepository.delete(user);

        // Xóa user khỏi Keycloak
        try {
            keycloak.realm(realm).users().get(userId).remove();
        } catch (Exception e) {
            log.error("Failed to delete user in Keycloak. User ID: {}", userId, e);
            throw new RuntimeException("Failed to delete user in Keycloak", e);
        }
    }

    @Override
    @Transactional
    public void changeUserPassword(String userId, String password) {
        // Tìm user trong database
        userRepository.findByKeycloakId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        try {
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(password);
            credentialRepresentation.setTemporary(false);

            keycloak.realm(realm).users().get(userId).resetPassword(credentialRepresentation);
        } catch (Exception e) {
            log.error("Failed to change user password in Keycloak. User ID: {}", userId, e);
            throw new RuntimeException("Failed to change user password in Keycloak", e);
        }
    }
}
