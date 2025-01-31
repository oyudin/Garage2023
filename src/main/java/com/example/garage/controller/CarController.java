package com.example.garage.controller;

import com.example.garage.model.Car;
import com.example.garage.model.Client;
import com.example.garage.service.CarService;
import com.example.garage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * change Garage to CarService
 */
@Controller
@RequestMapping("/carservice/clients")
public class CarController {

    private final CarService carService;
    private final ClientService clientService;

    @Autowired
    public CarController(CarService carService, ClientService clientService) {
        this.carService = carService;
        this.clientService = clientService;
    }

    @GetMapping("{ignoredClientId}/cars/add")
    public String showCarCreatingPage(@PathVariable Long ignoredClientId) {
        return "CarCreatingPage";
    }

    @GetMapping("{ignoredClientId}/cars/{carId}/update")
    public String showCarUpdatingPage(@PathVariable Long ignoredClientId) {
        return "UpdateCarPage";
    }

    @GetMapping("{ignoredClientId}/cars/{carId}")
    @ResponseBody
    public ResponseEntity<Car> getCar(@PathVariable Long ignoredClientId, @PathVariable Long carId) {
        Optional<Car> foundCar = carService.getCarById(carId);

        return foundCar.map(returnedCar -> ResponseEntity.ok().body(returnedCar))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{clientId}/cars")
    public String findCarsByClient(Model model, @PathVariable("clientId") long clientId) {
        Optional<Client> client = clientService.getClientById(clientId);
        List<Car> cars = carService.getCarsByClientId(clientId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().stream()
                .findFirst()
                .map(Object::toString)
                .orElse("USER"); // По умолчанию "USER", если роль не найдена

        model.addAttribute("userRole", role);

        client.ifPresent(value -> model.addAttribute("client", value));
        model.addAttribute("cars", cars);

        return "ClientCars";
    }

    @PostMapping("/{clientId}/cars")
    public String createCar(@PathVariable Long clientId, @RequestBody Car car) {
        carService.createCarForClient(clientId, car);
        return String.format("redirect:/carservice/clients/%d/cars", clientId);
    }

    @PatchMapping("{ignoredClientId}/cars/{carId}/update")
    public ResponseEntity<Car> updateCar(@PathVariable Long ignoredClientId, @PathVariable Long carId, @RequestBody Car car) {
        Optional<Car> foundCar = carService.getCarById(carId);

        if (foundCar.isPresent()) {
            carService.updateCar(carId, car);
            return ResponseEntity.ok().body(foundCar.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{ignoredClientId}/cars/{carId}/delete")
    public String deleteCar(@PathVariable Long ignoredClientId, @PathVariable Long carId) {
        Optional<Car> carToDelete = carService.getCarById(carId);

        if (carToDelete.isPresent()) {
            carService.deleteCar(carId);
            ResponseEntity.ok().body(carToDelete.get());
        } else {
            ResponseEntity.notFound().build();
        }
        return String.format("redirect:/carservice/clients/%s/cars", ignoredClientId);
    }
}
