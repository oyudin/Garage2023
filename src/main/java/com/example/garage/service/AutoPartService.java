package com.example.garage.service;

import com.example.garage.model.AutoPart;
import com.example.garage.repository.dao.AutoPartRepository;
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


//    public AutoPart getAutoPartByVinCode(String vinCode) {
//        return autoPartRepository.findAutoPartByVinCode(vinCode);
//    }

//    public AutoPart createAutoPart(AutoPart autoPart, int carId) {
//        return autoPartRepository.save(autoPart, carId);
//    }

    public void deleteAutoPartById(int autoPartId) {
//        autoPartRepository.deleteAutoPartById(autoPartId);
        jdbcTemplate.execute("DELETE  FROM auto_parts WHERE id = " + autoPartId);
    }

    public AutoPart save(AutoPart autoPart, int autoPartCarId) {
        jdbcTemplate.update("INSERT INTO  auto_parts (repairmentDate, auto_part_description, auto_part_price, auto_part_car_id, millage) " +
                        "VALUES (?, ?, ?, ?, ?)",
                autoPart.getRepairmentDate(),
                autoPart.getAutoPartDescription(),
                autoPart.getAutoPartPrice(),
                autoPartCarId,
                autoPart.getMillage());
        return autoPart;
    }

}
