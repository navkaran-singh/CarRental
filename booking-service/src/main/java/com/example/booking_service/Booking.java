package com.example.booking_service;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long carId; // Just the ID of the car
    private Long userId; // The ID of the user making the booking
    private LocalDate startDate;
    private LocalDate endDate;
}