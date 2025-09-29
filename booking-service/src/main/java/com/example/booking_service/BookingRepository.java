package com.example.booking_service;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
	// Returns bookings for a car that have not yet ended, ordered by start date
	List<Booking> findByCarIdAndEndDateGreaterThanEqualOrderByStartDateAsc(String carId, LocalDate date);
}