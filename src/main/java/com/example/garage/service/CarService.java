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

    // Метод для получения всех машин
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Метод для получения машины по id
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    // Метод для создания или обновления машины
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    // Метод для удаления машины по id
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    // Метод для получения всех машин клиента
    public List<Car> getCarsByClientId(Long clientId) {
        return carRepository.findByClientId(clientId);
    }

    public Car createCarForClient(int clientId, Car car) {
        Client client = clientRepository.findById(Long.valueOf(clientId))
                .orElseThrow(() -> new RuntimeException("Client not found with id " + clientId));

        car.setClient(client); // Устанавливаем связь между машиной и клиентом
        return carRepository.save(car);
    }
}
