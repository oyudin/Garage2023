package com.example.garage.controller;

import com.example.garage.model.Car;
import com.example.garage.model.ServiceHistory;
import com.example.garage.service.CarService;
import com.example.garage.service.ClientService;
import com.example.garage.service.ServiceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * change Garage to CarService
 */

@Controller
@RequestMapping("garage/clients/{ignoredClientId}/cars/{carId}/service-history")
public class ServiceHistoryController {

    private final ServiceHistoryService serviceHistoryService;
    private final CarService carService;
    private final ClientService clientService;

    @Autowired
    public ServiceHistoryController(ServiceHistoryService serviceHistoryService, ClientService clientService,
                                    CarService carService) {

        this.serviceHistoryService = serviceHistoryService;
        this.clientService = clientService;
        this.carService = carService;
    }

    @GetMapping()
    public String findServiceHistoryByCar(Model model, @PathVariable int ignoredClientId, @PathVariable Long carId) {
        Optional<Car> car = carService.getCarById(carId);
        List<ServiceHistory> serviceHistories = serviceHistoryService.getServiceHistoryByCar(carId);

        car.ifPresent(value -> model.addAttribute("car", value));
        model.addAttribute("serviceHistories", serviceHistories);

        return "ServiceHistoryByCar";
    }

    @GetMapping("/add")
    public String showServiceHistoryCreatingPage() {
        return "CreateServiceHistory";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<ServiceHistory> createServiceHistory(@PathVariable Long ignoredClientId, @PathVariable Long carId,
                                                               @RequestBody ServiceHistory serviceHistory) {
        ServiceHistory savedServiceHistory = serviceHistoryService.saveServiceHistoryForCar(carId, serviceHistory);

        if (clientService.getClientById(ignoredClientId).isPresent() && carService.getCarById(carId).isPresent()) {
            return ResponseEntity.ok().body(savedServiceHistory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
