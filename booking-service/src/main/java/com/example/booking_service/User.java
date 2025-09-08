package com.example.booking_service;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "app_user") // "user" can be a reserved keyword, so we use app_user
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String username;
    
    private String password;
    private String role = "USER"; 
}
