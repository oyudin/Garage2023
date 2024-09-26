package com.example.garage.service;

import com.example.garage.config.EncoderConfig;
import com.example.garage.model.AutoPart;
import com.example.garage.model.Car;
import com.example.garage.model.User;
import com.example.garage.repository.dao.AutoPartRepository;
import com.example.garage.repository.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoPartService {

    private final AutoPartRepository autoPartRepository;

    private final JdbcTemplate jdbcTemplate;

    public AutoPartService(AutoPartRepository autoPartRepository, JdbcTemplate jdbcTemplate) {
        this.autoPartRepository = autoPartRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AutoPart> getAllAutoParts() {
        return autoPartRepository.findAll();
    }

    public List<AutoPart> getListOfAutoPartsByCar(int carId) {
        return autoPartRepository.findAutoPartByAutoPartCarId(carId);
    }


    public AutoPart getAutoPartByName(String autoPartName) {
        return autoPartRepository.findAutoPartByAutoPartName(autoPartName);
    }

//    public AutoPart createAutoPart(AutoPart autoPart, int carId) {
//        return autoPartRepository.save(autoPart, carId);
//    }

    public void deleteAutoPartByName(String autoPartName) {
        autoPartRepository.deleteAutoPartByAutoPartName(autoPartName);
    }

    public AutoPart save(AutoPart autoPart, int autoPartCarId) {
        jdbcTemplate.update("INSERT INTO  auto_parts (auto_part_name, auto_part_description, auto_part_price, auto_part_car_id) " +
                        "VALUES (?, ?, ?, ?)",
                autoPart.getAutoPartName(),
                autoPart.getAutoPartDescription(),
                autoPart.getAutoPartPrice(),
                autoPartCarId);
        return autoPart;
    }

}
