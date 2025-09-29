package com.example.car_service;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cars")
@Data
public class Car {
    @Id
    private String id;

    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private boolean available = true;
}