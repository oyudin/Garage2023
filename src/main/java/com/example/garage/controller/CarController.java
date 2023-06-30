package com.example.garage.controller;

import com.example.garage.exception.CarNotFound;
import com.example.garage.model.Car;
import com.example.garage.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/garage/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "Cars";
    }

    @PostMapping("/{carId}")
    public @ResponseBody void updateCar(@PathVariable int carId, @RequestBody Car car) throws CarNotFound {
        this.carService.updateCar(car, carId);
    }

    @DeleteMapping("/{carId}")
    public @ResponseBody void deleteCar(@PathVariable int carId) {
        this.carService.deleteCar(carId);
    }
}
