//package com.example.garage.repository;
//
//import com.example.garage.model.AutoPart;
//import com.example.garage.model.Car;
//import com.example.garage.repository.dao.AutoPartRepository;
//import com.example.garage.repository.dao.CarRepository;
//import com.example.garage.repository.mapper.CarMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//import static com.example.garage.repository.SQLQueries.*;
//
//@Repository
//public class AutoPartImpl implements AutoPartRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public AutoPartImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public AutoPart save(AutoPart autoPart, int autoPartCarId) {
//        jdbcTemplate.update("INSERT INTO  auto_parts (auto_part_name, auto_part_description, auto_part_price, auto_part_car_id) " +
//                        "VALUES (?, ?, ?, ?)",
//                autoPart.getAutoPartName(),
//                autoPart.getAutoPartDescription(),
//                autoPart.getAutoPartPrice(),
//                autoPartCarId);
//        return autoPart;
//    }
//}