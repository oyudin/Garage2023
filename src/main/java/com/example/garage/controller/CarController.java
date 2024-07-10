package com.example.garage.controller;

import com.example.garage.exception.CarNotFound;
import com.example.garage.model.Car;
import com.example.garage.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/garage")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "Cars";
    }

    @GetMapping("/cars/{carId}")
    public @ResponseBody Car getCarById(@PathVariable int carId) throws CarNotFound {
        return this.carService.getCar(carId);
    }

    @PostMapping("/cars/{carId}")
    public @ResponseBody void updateCar(@PathVariable int carId, @RequestBody Car car) throws CarNotFound {
        this.carService.updateCar(car, carId);
    }

    @GetMapping("/persons/{personId}/newCar")
    public String showCarCreatingPage() {
        return "CarCreatingPage";
    }

    @PostMapping("/persons/{personId}/addCar")
    public String createCarToPerson(@PathVariable int personId, @RequestBody Car car) {
        carService.addCar(personId, car);
        return "redirect:/garage/persons/" + personId + "/cars";
    }

    @DeleteMapping("/cars/{carId}")
    public @ResponseBody void deleteCar(@PathVariable int carId) {
        this.carService.deleteCar(carId);
    }
}
