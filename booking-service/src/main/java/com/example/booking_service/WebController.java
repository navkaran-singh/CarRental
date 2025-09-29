package com.example.booking_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
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

        Map<String, LocalDate> nextAvailableByCarId = new HashMap<>();
        Map<String, Boolean> isBookedToday = new HashMap<>();
        Map<String, Boolean> isUnavailable = new HashMap<>();
        Map<String, LocalDate> availableFromDisplay = new HashMap<>();
		LocalDate today = LocalDate.now();
		if (cars != null) {
			for (CarDto car : cars) {
				if (car == null) continue;
                var allBookings = bookingRepository.findByCarIdOrderByStartDateAsc(car.getId());
                LocalDate maxEndAcrossAll = null;
                for (var b : allBookings) {
                    if (maxEndAcrossAll == null || b.getEndDate().isAfter(maxEndAcrossAll)) {
                        maxEndAcrossAll = b.getEndDate();
                    }
                }
                var futureBookings = bookingRepository
                    .findByCarIdAndEndDateGreaterThanEqualOrderByStartDateAsc(car.getId(), today);
                boolean hasAny = !futureBookings.isEmpty();
				if (!hasAny) {
					nextAvailableByCarId.put(car.getId(), today);
					isBookedToday.put(car.getId(), false);
                    isUnavailable.put(car.getId(), false);
                } else {
                    LocalDate next = today;
                    boolean bookedNow = false;
                    for (var b : futureBookings) {
                        boolean overlapsToday = !b.getStartDate().isAfter(today) && !b.getEndDate().isBefore(today);
                        if (overlapsToday) {
                            bookedNow = true;
                        }
                        if (!b.getStartDate().isAfter(next)) {
                            next = b.getEndDate().plusDays(1);
                        }
                    }
                    nextAvailableByCarId.put(car.getId(), next);
                    isBookedToday.put(car.getId(), bookedNow);
                    isUnavailable.put(car.getId(), true);
                }
                if (maxEndAcrossAll != null) {
                    availableFromDisplay.put(car.getId(), maxEndAcrossAll);
                }
			}
		}
		model.addAttribute("nextAvailable", nextAvailableByCarId);
        model.addAttribute("isBookedToday", isBookedToday);
        model.addAttribute("isUnavailable", isUnavailable);
        model.addAttribute("availableFromDisplay", availableFromDisplay);
		return "home";
	}

	@GetMapping("/cars/new")
	public String addCarForm() {
		return "add-car";
	}

	@PostMapping("/cars")
	public String createCar(@RequestParam("make") String make,
							@RequestParam("model") String modelName,
							@RequestParam("year") int year,
							@RequestParam("licensePlate") String licensePlate,
							Model model) {
		Map<String, Object> car = new HashMap<>();
		car.put("make", make);
		car.put("model", modelName);
		car.put("year", year);
		car.put("licensePlate", licensePlate);
		car.put("available", true);
		try {
			restTemplate.postForObject("http://localhost:8081/api/cars", car, String.class);
			model.addAttribute("success", "Car added successfully");
			return home(model);
		} catch (RestClientException ex) {
			model.addAttribute("error", "Failed to add car: " + ex.getMessage());
			return "add-car";
		}
	}

	@PostMapping("/book")
    public String book(@RequestParam("carId") String carId,
					  @RequestParam("startDate") String startDateStr,
					  @RequestParam("endDate") String endDateStr,
					  Model model) {
		LocalDate start = LocalDate.parse(startDateStr);
		LocalDate end = LocalDate.parse(endDateStr);
		if (end.isBefore(start)) {
			model.addAttribute("error", "End date must be on or after start date");
			return home(model);
		}
		var conflicts = bookingRepository
			.findByCarIdAndEndDateGreaterThanEqualOrderByStartDateAsc(carId, start);
		for (var b : conflicts) {
			boolean overlaps = !b.getStartDate().isAfter(end) && !b.getEndDate().isBefore(start);
			if (overlaps) {
				model.addAttribute("error", "Car already booked in selected range");
				return home(model);
			}
		}
		Booking booking = new Booking();
        booking.setCarId(carId);
		booking.setStartDate(start);
		booking.setEndDate(end);
		bookingRepository.save(booking);

        // Mark car as unavailable in car-service using path-based POST endpoint
        try {
            ResponseEntity<String> resp = restTemplate.exchange(
                "http://localhost:8081/api/cars/" + carId + "/availability/false",
                HttpMethod.POST,
                null,
                String.class
            );
            if (!resp.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("error", "Booking saved but failed to update car availability (" + resp.getStatusCode() + ")");
                return home(model);
            }
        } catch (RestClientException ex) {
            model.addAttribute("error", "Booking saved but failed to update car availability. Please refresh.");
            return home(model);
        }

        // Force the view to show the user's selected end date for this car on the next render
        model.addAttribute("justBookedCarId", carId);
        model.addAttribute("justBookedEndDate", end);

		model.addAttribute("success", "Booking confirmed");
		return home(model);
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