package com.example.garage.repository.mapper;

import com.example.garage.model.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet rows, int rowNum) throws SQLException {
        return Car.builder()
                .id(rows.getInt("car_id"))
                .number(rows.getString("number"))
                .vinCode(rows.getString("vincode"))
                .brand(rows.getString("brand"))
                .model(rows.getString("model"))
                .personId(rows.getInt("person_id"))
                .autoPartId(rows.getInt("autopart_id"))
                .build();
    }
//    @Override
//    public Person mapRow(ResultSet rows, int rowNum) throws SQLException {
//        return new Person(rows.getInt("id"),
//                rows.getString("name"),
//                rows.getString("surname"));
//    }


    // MAP Struckt
}
