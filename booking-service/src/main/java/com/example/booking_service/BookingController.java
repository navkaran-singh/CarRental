package com.example.booking_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        // In a real app, you would add logic here to check if the car is available
        // by calling the car-service. For now, we just save the booking.
        return bookingRepository.save(booking);
    }
}