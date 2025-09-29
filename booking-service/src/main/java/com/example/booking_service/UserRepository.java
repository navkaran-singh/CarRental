package com.example.booking_service;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    // This custom method is needed by Spring Security to find a user by their username
    Optional<User> findByUsername(String username);
}