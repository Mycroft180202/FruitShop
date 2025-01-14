package com.example.fruit_api.service;

import com.example.fruit_api.dto.UserDTO;
import com.example.fruit_api.common.api.exception.UserNotFoundException;
import com.example.fruit_api.mapper.UserMapper;
import com.example.fruit_api.models.User;
import com.example.fruit_api.repository.UserRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakUserQueryServiceImpl implements  KeycloakUserQueryService{

    private final UserRepository userRepository;

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        try {
            Page<User> users = userRepository.findAll(pageable);
            return users.map(UserMapper::toDTO);
        } catch (Exception e) {
            log.error("Error fetching all users from the database", e);
            throw new RuntimeException("Error fetching all users from the database", e);
        }
    }

    @Override
    public UserDTO getUserById(String userId) {
        try {
            User user = userRepository.findByKeycloakId(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
            return UserMapper.toDTO(user);
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error fetching user by ID from the database", e);
            throw new RuntimeException("Error fetching user by ID from the database", e);
        }
    }

    @Override
    public UserDTO getUserByUsername(String userName) {
        try {
            User user = userRepository.findByUserName(userName)
                    .orElseThrow(() -> new UserNotFoundException("User not found with username: " + userName));
            return UserMapper.toDTO(user);
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error fetching user by username from the database", e);
            throw new RuntimeException("Error fetching user by username from the database", e);
        }
    }

    @Override
    public Page<UserDTO> searchUsers(String fullName, Boolean active, Pageable pageable) {
        try {
            Specification<User> spec = (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (fullName != null && !fullName.trim().isEmpty()) {
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(criteriaBuilder.trim(root.get("fullName"))),
                            "%" + fullName.toLowerCase().trim() + "%"
                    ));
                }
                if (active != null) {
                    predicates.add(criteriaBuilder.equal(root.get("active"), active));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            Page<User> users = userRepository.findAll(spec, pageable);
            return users.map(UserMapper::toDTO);
        } catch (Exception e) {
            log.error("Error searching users", e);
            throw new RuntimeException("Error searching users", e);
        }
    }
}
