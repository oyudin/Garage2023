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
                .brand(rows.getString("brand"))
                .model(rows.getString("model"))
                .color(rows.getString("color"))
                .personId(rows.getInt("person_id"))
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
