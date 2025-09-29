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
}