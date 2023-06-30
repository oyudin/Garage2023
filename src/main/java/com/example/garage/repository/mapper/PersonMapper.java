package com.example.garage.repository.mapper;

import com.example.garage.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rows, int rowNum) throws SQLException {
        return Person.builder()
                .id(rows.getInt("id"))
                .name(rows.getString("name"))
                .surname(rows.getString("surname"))
                .phoneNumber(rows.getLong("phoneNumber"))
                .build();
    }
//    @Override
//    public Person mapRow(ResultSet rows, int rowNum) throws SQLException {
//        return new Person(rows.getInt("id"),
//                rows.getString("name"),
//                rows.getString("surname"));
//    }
}
