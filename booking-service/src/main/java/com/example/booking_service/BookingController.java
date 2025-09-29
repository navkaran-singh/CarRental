package com.example.booking_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@PostMapping
	public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
		if (booking.getCarId() == null || booking.getStartDate() == null || booking.getEndDate() == null) {
			return ResponseEntity.badRequest().body("carId, startDate, and endDate are required");
		}
		if (booking.getEndDate().isBefore(booking.getStartDate())) {
			return ResponseEntity.badRequest().body("endDate must be on or after startDate");
		}

		LocalDate start = booking.getStartDate();
		LocalDate end = booking.getEndDate();
		List<Booking> conflicts = bookingRepository
			.findByCarIdAndEndDateGreaterThanEqualOrderByStartDateAsc(booking.getCarId(), start);
		for (Booking b : conflicts) {
			boolean overlaps = !b.getStartDate().isAfter(end) && !b.getEndDate().isBefore(start);
			if (overlaps) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Car already booked in selected range");
			}
		}

		Booking saved = bookingRepository.save(booking);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
}