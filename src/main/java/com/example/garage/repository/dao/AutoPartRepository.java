package com.example.garage.repository.dao;

import com.example.garage.model.AutoPart;
import com.example.garage.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutoPartRepository extends CrudRepository<AutoPart, Integer> {

    List<AutoPart> findAll();

    AutoPart findAutoPartByAutoPartName(String userSurname);

    List<AutoPart> findAutoPartByAutoPartCarId(int carId);
    AutoPart save(AutoPart autoPart);

    AutoPart deleteAutoPartByAutoPartName(String autoPartName);
}
