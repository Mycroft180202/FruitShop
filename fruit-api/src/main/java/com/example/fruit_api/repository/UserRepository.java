package com.example.fruit_api.repository;

import com.example.fruit_api.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByKeycloakId(String keycloakId);

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    void deleteByKeycloakId(String keycloakId);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE (:fullName IS NULL OR LOWER(REPLACE(u.fullName, ' ', '')) LIKE LOWER(REPLACE(CONCAT('%', :fullName, '%'), ' ', ''))) " +
            "AND (:active IS NULL OR u.active = :active)")
    Page<User> searchByFullNameOrActive(@Param("fullName") String fullName, @Param("active") Boolean active, Pageable pageable);
}
