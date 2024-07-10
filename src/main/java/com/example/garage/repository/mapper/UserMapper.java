package com.example.garage.repository.mapper;

import com.example.garage.model.Car;
import com.example.garage.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rows, int rowNum) throws SQLException {
        return User.builder()
                .id(rows.getInt("id"))
                .username("username")
                .password("password")
                .role("role")
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
