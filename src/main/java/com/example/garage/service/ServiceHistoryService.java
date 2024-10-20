package com.example.garage.service;

import com.example.garage.model.Car;
import com.example.garage.model.Client;
import com.example.garage.model.ServiceHistory;
import com.example.garage.repository.CarRepository;
import com.example.garage.repository.ClientRepository;
import com.example.garage.repository.ServiceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceHistoryService {

    private final ServiceHistoryRepository serviceHistoryRepository;
    private final CarRepository carRepository;

    @Autowired
    public ServiceHistoryService(ServiceHistoryRepository serviceHistoryRepository, CarRepository carRepository) {
        this.serviceHistoryRepository = serviceHistoryRepository;
        this.carRepository = carRepository;
    }

    public List<ServiceHistory> getAllServiceHistory() {
        return serviceHistoryRepository.findAll();
    }

    public ServiceHistory saveServiceHistory(ServiceHistory serviceHistory) {
        return serviceHistoryRepository.save(serviceHistory);
    }

    public ServiceHistory saveServiceHistoryForCar(long carId, ServiceHistory serviceHistory) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found with id " + carId));
        serviceHistory.setCar(car);
        return serviceHistoryRepository.save(serviceHistory);
    }
}
