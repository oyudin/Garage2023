package com.example.garage.repository;

import com.example.garage.model.Car;
import com.example.garage.repository.dao.CarRepository;
import com.example.garage.repository.mapper.CarMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.garage.repository.SQLQueries.*;

@Repository
public class CarImpl implements CarRepository {

    private final JdbcTemplate jdbcTemplate;

    public CarImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> findAllCars() {
        return jdbcTemplate.query(SELECT_ALL_CARS.query, new CarMapper());
    }

    @Override
    public Car saveCar(Car car) {
        jdbcTemplate.update(SAVE_CAR.query, car.getNumber(), car.getBrand(), car.getModel(), car.getColor());
        return car;
    }

    @Override
    public Car updateCar(Car car, int carId) {
        jdbcTemplate.update(UPDATE_CAR.query + carId,
                car.getNumber(), car.getBrand(), car.getModel(), car.getColor());
        return car;
    }

    @Override
    public Car deleteCar(int carId) {
        jdbcTemplate.execute(DELETE_CAR.query + carId);
        return null;
    }
}