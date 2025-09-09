package com.example.booking_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class WebController {

	private final RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private BookingRepository bookingRepository;

	@GetMapping
	public String home(Model model) {
		CarDto[] cars = restTemplate.getForObject("http://localhost:8081/api/cars", CarDto[].class);
		model.addAttribute("cars", cars);

		Map<Long, LocalDate> nextAvailableByCarId = new HashMap<>();
		LocalDate today = LocalDate.now();
		if (cars != null) {
			for (CarDto car : cars) {
				if (car == null) continue;
				var futureBookings = bookingRepository
					.findByCarIdAndEndDateGreaterThanEqualOrderByStartDateAsc(car.getId(), today);
				if (futureBookings.isEmpty()) {
					nextAvailableByCarId.put(car.getId(), today);
				} else {
					// Next availability is the day after the last overlapping/adjacent booking ends
					LocalDate next = today;
					for (var b : futureBookings) {
						if (!b.getStartDate().isAfter(next)) {
							next = b.getEndDate().plusDays(1);
						}
					}
					nextAvailableByCarId.put(car.getId(), next);
				}
			}
		}
		model.addAttribute("nextAvailable", nextAvailableByCarId);
		return "home";
	}

	@GetMapping("/register")
	public String showRegister() {
		return "register";
	}

	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
} 