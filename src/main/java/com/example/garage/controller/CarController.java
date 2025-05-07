package com.example.garage.controller;

import com.example.garage.model.Car;
import com.example.garage.model.Client;
import com.example.garage.service.CarService;
import com.example.garage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * change Garage to CarService
 */
@Controller
@RequestMapping("garage/clients")
public class CarController {

    private final CarService carService;
    private final ClientService clientService;

    @Autowired
    public CarController(CarService carService, ClientService clientService) {
        this.carService = carService;
        this.clientService = clientService;
    }

    @GetMapping("/{clientId}/cars")
    public String findCarsByClient(Model model, @PathVariable("clientId") long clientId) {
        Optional<Client> client = clientService.getClientById(clientId);
        List<Car> cars = carService.getCarsByClientId(clientId);

        client.ifPresent(value -> model.addAttribute("client", value));
        model.addAttribute("cars", cars);

        return "ClientCars";
    }

    @PostMapping("/{clientId}/cars")
    public String createCar(@PathVariable Long clientId, @RequestBody Car car) {
        carService.createCarForClient(clientId, car);
        return String.format("redirect:/garage/clients/%d/cars", clientId);
    }

    @GetMapping("{ignoredClientId}/cars/add")
    public String showCarCreatingPage(@PathVariable Long ignoredClientId) {
        return "CarCreatingPage";
    }
}
