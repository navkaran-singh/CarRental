package com.example.car_service;

import jakarta.persistence.Column; // <-- Make sure this is imported
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String make;
    private String model;

    @Column(name = "car_year") // <-- ADD THIS LINE
    private int year;
    
    private String licensePlate;
    private boolean available = true;
}