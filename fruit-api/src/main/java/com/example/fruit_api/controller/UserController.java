package com.example.fruit_api.controller;

import com.example.fruit_api.common.api.response.ApiResponse;
import com.example.fruit_api.dto.UserCreateDTO;
import com.example.fruit_api.dto.UserDTO;
import com.example.fruit_api.dto.UserUpdateDTO;
import com.example.fruit_api.service.KeycloakUserCommandService;
import com.example.fruit_api.service.KeycloakUserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final KeycloakUserCommandService userCommandService;
    private final KeycloakUserQueryService userQueryService;

    @Value("${keycloak.server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.client-id}")
    private String keycloakClientId;

    @Value("${keycloak.client-secret}")
    private String keycloakClientSecret;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        // 1. Prepare the request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "password");
        requestBody.add("client_id", keycloakClientId);
        requestBody.add("username", username);
        requestBody.add("password", password);
        requestBody.add("client_secret", keycloakClientSecret);

        // 2. Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 3. Create the HTTP entity with headers and body
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);

        // 4. Send the request to Keycloak
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = null;
        try {
            response = restTemplate.exchange(
                    keycloakServerUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/token",
                    HttpMethod.POST,
                    entity,
                    Map.class
            );
        } catch (HttpClientErrorException ex) {
            // Handle invalid credentials, for example return 401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        // 5. Process the response (Handle success and error cases)
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();

            // 6. Return the tokens to the frontend
            return ResponseEntity.ok().body(responseBody);
        } else {
            // Handle error (e.g., invalid credentials)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @GetMapping("/userinfo")
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