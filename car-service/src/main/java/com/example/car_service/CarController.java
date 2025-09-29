package com.example.car_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars") // <-- This must match the path
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable String id) {
        return carRepository.findById(id);
    }

    @PostMapping // <-- This annotation maps the POST method
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @PatchMapping("/{id}/availability")
    public Car updateAvailability(@PathVariable String id, @RequestParam("available") boolean available) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setAvailable(available);
        return carRepository.save(car);
    }

    @PostMapping("/{id}/availability")
    public Car updateAvailabilityPost(@PathVariable String id, @RequestParam("available") boolean available) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setAvailable(available);
        return carRepository.save(car);
    }

    @PostMapping("/{id}/availability/{available}")
    public Car updateAvailabilityPostPath(@PathVariable String id, @PathVariable boolean available) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setAvailable(available);
        return carRepository.save(car);
    }
}