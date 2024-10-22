package com.example.garage.service;

import com.example.garage.model.Car;
import com.example.garage.model.ServiceHistory;
import com.example.garage.repository.CarRepository;
import com.example.garage.repository.ServiceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceHistoryService {

    private final ServiceHistoryRepository serviceHistoryRepository;
    private final CarRepository carRepository;

    @Autowired
    public ServiceHistoryService(ServiceHistoryRepository serviceHistoryRepository, CarRepository carRepository) {
        this.serviceHistoryRepository = serviceHistoryRepository;
        this.carRepository = carRepository;
    }

    public List<ServiceHistory> getServiceHistoryByCar(Long carId) {
        return serviceHistoryRepository.findByCarId(carId);
    }

    public ServiceHistory saveServiceHistoryForCar(long carId, ServiceHistory serviceHistory) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found with id " + carId));
        serviceHistory.setCar(car);
        return serviceHistoryRepository.save(serviceHistory);
    }

    public void deleteServiceHistory(long id) {
        ServiceHistory serviceHistory = serviceHistoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Service History not found with id " + id));
        serviceHistoryRepository.delete(serviceHistory);
    }
}
