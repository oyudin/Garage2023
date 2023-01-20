package com.example.garage.repository.dao;

import com.example.garage.model.Car;


import java.util.List;

public interface CarRepository {

    List<Car> findAllCars();

    Car saveCar(Car car);

    Car updateCar(Car car, int id);

    Car deleteCar(int id);
}
