package com.example.booking_service;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "bookings")
@Data
public class Booking {
    @Id
    private String id;

    private String carId;
    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;
}