package com.example.garage.repository.dao;

import com.example.garage.model.Car;
import com.example.garage.model.Person;


import java.util.List;

public interface CarRepository {

    List<Car> findAllCars();

    Car saveCar(int personId, Car car);

    Car updateCar(Car car, int id);

    Car deleteCar(int id);
}
