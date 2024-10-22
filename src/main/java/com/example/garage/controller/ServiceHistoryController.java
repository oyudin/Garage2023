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
@RequestMapping("garage/clients/{ignoredClientId}/cars/{ignoredCarId}/service-history")
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
    public String findServiceHistoryByCar(Model model, @PathVariable int ignoredClientId, @PathVariable Long ignoredCarId) {
        Optional<Car> car = carService.getCarById(ignoredCarId);
        List<ServiceHistory> serviceHistories = serviceHistoryService.getServiceHistoryByCar(ignoredCarId);

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
    public ResponseEntity<ServiceHistory> createServiceHistory(@PathVariable Long ignoredClientId, @PathVariable Long ignoredCarId,
                                                               @RequestBody ServiceHistory serviceHistory) {
        ServiceHistory savedServiceHistory = serviceHistoryService.saveServiceHistoryForCar(ignoredCarId, serviceHistory);

        if (clientService.getClientById(ignoredClientId).isPresent() && carService.getCarById(ignoredCarId).isPresent()) {
            return ResponseEntity.ok().body(savedServiceHistory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public String deleteServiceHistory(@PathVariable Long id, @PathVariable String ignoredClientId, @PathVariable String ignoredCarId) {
        serviceHistoryService.deleteServiceHistory(id);
        return String.format("redirect:/garage/clients/%s/cars/%s/service-history", ignoredClientId, ignoredCarId);
    }

}
