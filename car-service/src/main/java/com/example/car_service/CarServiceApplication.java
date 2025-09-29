package com.example.car_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarServiceApplication.class, args);
	}

    @Bean
    CommandLineRunner seedData(CarRepository carRepository) {
        return args -> {
            if (carRepository.count() == 0) {
                Car c1 = new Car();
                c1.setMake("Toyota");
                c1.setModel("Corolla");
                c1.setYear(2020);
                c1.setLicensePlate("DL1ABC1234");
                c1.setAvailable(true);

                Car c2 = new Car();
                c2.setMake("Honda");
                c2.setModel("Civic");
                c2.setYear(2019);
                c2.setLicensePlate("MH12XYZ5678");
                c2.setAvailable(true);

                Car c3 = new Car();
                c3.setMake("Hyundai");
                c3.setModel("i20");
                c3.setYear(2021);
                c3.setLicensePlate("GJ05PQ7890");
                c3.setAvailable(true);

                carRepository.save(c1);
                carRepository.save(c2);
                carRepository.save(c3);
            }
        };
    }
}
