package com.example.garage.service;

import com.example.garage.model.Car;
import com.example.garage.model.Client;
import com.example.garage.repository.CarRepository;
import com.example.garage.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public CarService(CarRepository carRepository, ClientRepository clientRepository) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> getCarsByClientId(Long clientId) {
        return carRepository.findByClientId(clientId);
    }

    public Car createCarForClient(Long clientId, Car car) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + clientId));

        car.setClient(client);
        return carRepository.save(car);
    }

}
