package com.example.booking_service;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	// Returns bookings for a car that have not yet ended, ordered by start date
	List<Booking> findByCarIdAndEndDateGreaterThanEqualOrderByStartDateAsc(Long carId, LocalDate date);
}