package com.example.garage.service;

import com.example.garage.exception.CarNotFound;
import com.example.garage.model.Car;
import com.example.garage.repository.dao.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class CarServiceTest {
    private final CarRepository carRepository = Mockito.mock(CarRepository.class);
    private final CarService carService = new CarService(carRepository);
    private final Car car = Car.builder().id(1).number("AA1234WW").brand("WV").model("Golf").color("White").build();

    @Test
    public void getAllCarsTest() {
        List<Car> cars = new ArrayList<>();
        cars.add(car);

        Mockito.when(carRepository.findAllCars()).thenReturn(cars);
        List<Car> cars1 = carService.getAllCars();
        Assertions.assertEquals(cars1, cars);
    }

    @Test
    public void addCarTest() {
        Mockito.when(carRepository.saveCar(any())).thenReturn(car);
        Car car1 = carService.addCar(new Car());
        Assertions.assertEquals(car1, car);
    }

    @Test
    public void updateCarTest() throws CarNotFound {
        Mockito.when(carRepository.updateCar(any(), anyInt())).thenReturn(car);
        Car car1 = carService.updateCar(new Car(), 1);
        Assertions.assertEquals(car1, car);
    }

    @Test
    public void deleteCarTest() {
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        cars.add(car);

        Mockito.when(carRepository.deleteCar(anyInt())).thenReturn(cars.remove(car.getId()));
        Assertions.assertEquals(1, cars.size());
    }
}



















