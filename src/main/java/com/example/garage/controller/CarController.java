package com.example.garage.controller;

import com.example.garage.model.Car;
import com.example.garage.model.Client;
import com.example.garage.service.CarService;
import com.example.garage.service.ClientService;
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

//@RestController
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

    // Получение всех машин
//    @GetMapping
//    public ResponseEntity<List<Car>> getAllCars() {
//        List<Car> cars = carService.getAllCars();
//        return ResponseEntity.ok(cars);
//    }

    @GetMapping("/{clientId}/cars")
    public String findCarsByClient(Model model, @PathVariable("clientId") long clientId) {
        Optional<Client> client = clientService.getClientById(clientId); // Получаем клиента
        List<Car> cars = carService.getCarsByClientId(clientId); // Получаем машины клиента

        client.ifPresent(value -> model.addAttribute("client", value));
        model.addAttribute("cars", cars); // Добавляем машины клиента в модель

        return "ClientCars";
    }

    // Получение машины по id
//    @GetMapping("/{id}")
//    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
//        Optional<Car> car = carService.getCarById(id);
//        return car.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }


    // Создание новой машины
//    @PostMapping
//    public ResponseEntity<Car> createCar(@RequestBody Car car) {
//        Car savedCar = carService.saveCar(car);
//        return ResponseEntity.ok(savedCar);
//    }

    // Обновление информации о машине
//    @PutMapping("/{id}")
//    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
//        Optional<Car> existingCar = carService.getCarById(id);
//        if (existingCar.isPresent()) {
//            updatedCar.setId(id); // Устанавливаем ID, чтобы обновить правильную машину
//            Car savedCar = carService.saveCar(updatedCar);
//            return ResponseEntity.ok(savedCar);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    // Удаление машины
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
//        if (carService.getCarById(id).isPresent()) {
//            carService.deleteCar(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @PostMapping("/{clientId}/cars")
//    public ResponseEntity<Car> createCar(@PathVariable int clientId, @RequestBody Car car) {
//        if (clientService.getClientById(clientId).isPresent()) {
//            Car createdCar = carService.createCarForClient(clientId, car);
//            return ResponseEntity.ok().body(createdCar);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping("/{clientId}/cars")
    public String createCar(@PathVariable int clientId, @RequestBody Car car) {
//        if (clientService.getClientById(clientId).isPresent()) {
            Car createdCar = carService.createCarForClient(clientId, car);
            return "redirect:/garage/clients/" + clientId + "/cars";
//            return ResponseEntity.ok().body(createdCar);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }

    //
    @GetMapping("{clientId}/cars/add")
    public String showCarCreatingPage() {
        return "CarCreatingPage";
    }


}
