package com.example.booking_service;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // This custom method is needed by Spring Security to find a user by their username
    Optional<User> findByUsername(String username);
}