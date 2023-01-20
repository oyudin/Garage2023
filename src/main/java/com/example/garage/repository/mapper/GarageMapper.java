package com.example.garage.repository.mapper;

import com.example.garage.model.Garage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GarageMapper implements RowMapper<Garage> {
    @Override
    public Garage mapRow(ResultSet rows, int rowNum) throws SQLException {
        return Garage.builder()
                .personId(rows.getInt("id"))
                .name(rows.getString("name"))
                .surname(rows.getString("surname"))
                .carId(rows.getInt("car_id"))
                .number(rows.getString("number"))
                .brand(rows.getString("brand"))
                .model(rows.getString("model"))
                .color(rows.getString("color"))
                .carPersonId(rows.getInt("person_id"))
                .build();
    }

    // MAP Struckt
}
