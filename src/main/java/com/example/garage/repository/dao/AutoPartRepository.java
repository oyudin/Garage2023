package com.example.garage.repository.dao;

import com.example.garage.model.AutoPart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutoPartRepository extends CrudRepository<AutoPart, Integer> {

    List<AutoPart> findAll();

//    AutoPart findAutoPartByVinCode(String vinCode);

    List<AutoPart> findAutoPartByAutoPartCarId(int carId);

    //AutoPart save(AutoPart autoPart, int autoPartCarId);

//    void deleteAutoPartById(int autoPartId);
}
