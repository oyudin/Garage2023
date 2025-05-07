package com.example.garage.controller;

import com.example.garage.model.ServiceHistory;
import com.example.garage.service.CarService;
import com.example.garage.service.ClientService;
import com.example.garage.service.ServiceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("garage/clients/{ignoredClientId}/cars/{ignoredCarId}/service-history")
public class PrintServiceHistoryController {

    private final ServiceHistoryService serviceHistoryService;
    private final CarService carService;
    private final ClientService clientService;

    @Autowired
    public PrintServiceHistoryController(ServiceHistoryService serviceHistoryService, ClientService clientService,
                                         CarService carService) {

        this.serviceHistoryService = serviceHistoryService;
        this.clientService = clientService;
        this.carService = carService;
    }

    @GetMapping("/{serviceHistoryId}/print")
    @ResponseBody
    public String printServiceHistory(@PathVariable int ignoredClientId,
                                      @PathVariable Long ignoredCarId,
                                      @PathVariable Long serviceHistoryId) {
        Optional<ServiceHistory> serviceHistory = serviceHistoryService.getServiceHistoryById(serviceHistoryId);

        // Получаем ServiceHistory из базы данных
        return serviceHistory.map(serviceHistoryService::generateServiceHistoryTable).orElse(null);
    }

    @GetMapping("/printAll")
    @ResponseBody
    public String printAllServiceHistory(@PathVariable int ignoredClientId,
                                         @PathVariable Long ignoredCarId) {
        List<ServiceHistory> serviceHistories = serviceHistoryService.getServiceHistoryByCar(ignoredCarId);

        return serviceHistoryService.generateFullServiceHistoryTable(serviceHistories);
        // Получаем ServiceHistory из базы данных
//        return serviceHistory.map(serviceHistoryService::generateServiceHistoryTable).orElse(null);
    }
}
