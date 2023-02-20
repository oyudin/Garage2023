package com.example.garage.repository.dao;

import com.example.garage.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByUsername(String userSurname);
}
