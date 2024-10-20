package com.example.garage.repository;

import com.example.garage.model.Car;
import com.example.garage.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByClientId(Long clientId);

}
