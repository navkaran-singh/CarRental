package com.example.booking_service;

import lombok.Data;

@Data
public class CarDto {
    private String id;
	private String make;
	private String model;
	private int year;
	private String licensePlate;
	private boolean available;
} 