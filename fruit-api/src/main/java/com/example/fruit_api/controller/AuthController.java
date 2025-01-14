package com.example.fruit_api.controller;

import com.example.fruit_api.common.api.response.ApiResponse;
import com.example.fruit_api.dto.UserCreateDTO;
import com.example.fruit_api.dto.UserDTO;
import com.example.fruit_api.dto.UserUpdateDTO;
import com.example.fruit_api.service.KeycloakUserCommandService;
import com.example.fruit_api.service.KeycloakUserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final KeycloakUserCommandService userCommandService;
    private final KeycloakUserQueryService userQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<UserDTO> page = userQueryService.getAllUsers(pageable);
        return ResponseEntity.ok(ApiResponse.success(page.getContent(), page));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        UserDTO createdUser = userCommandService.createUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(createdUser));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable String userId) {
        UserDTO user = userQueryService.getUserById(userId);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        UserDTO updatedUser = userCommandService.updateUser(userId, userUpdateDTO);
        return ResponseEntity.ok(ApiResponse.success(updatedUser));
    }

    @GetMapping("/by-userName/{userName}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserByUsername(@PathVariable String userName) {
        UserDTO user = userQueryService.getUserByUsername(userName);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String userId) {
        userCommandService.deleteUser(userId);
        return ResponseEntity.ok(ApiResponse.success("Delete user successfully!"));
    }

    @PutMapping("/{userId}/enable")
    public ResponseEntity<ApiResponse<UserDTO>> enableUser(@PathVariable String userId) {
        UserDTO enabledUser = userCommandService.enableUser(userId);
        return ResponseEntity.ok(ApiResponse.success(enabledUser));
    }

    @PutMapping("/{userId}/disable")
    public ResponseEntity<ApiResponse<UserDTO>> disableUser(@PathVariable String userId) {
        UserDTO disabledUser = userCommandService.disableUser(userId);
        return ResponseEntity.ok(ApiResponse.success(disabledUser));
    }

    @PutMapping("/{userId}/change-password")
    public ResponseEntity<ApiResponse<Void>> changeUserPassword(
            @PathVariable String userId,
            @RequestBody ChangePasswordRequest request) {
        userCommandService.changeUserPassword(userId, request.getPassword());
        return ResponseEntity.ok(ApiResponse.success("Password updated successfully"));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UserDTO>>> searchUsers(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) Boolean active,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<UserDTO> page = userQueryService.searchUsers(fullName, active, pageable);
        return ResponseEntity.ok(ApiResponse.success(page.getContent(), page));
    }
}