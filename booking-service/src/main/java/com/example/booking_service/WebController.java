package com.example.booking_service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/")
public class WebController {

	private final RestTemplate restTemplate = new RestTemplate();

	@GetMapping
	public String home(Model model) {
		CarDto[] cars = restTemplate.getForObject("http://localhost:8081/api/cars", CarDto[].class);
		model.addAttribute("cars", cars);
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