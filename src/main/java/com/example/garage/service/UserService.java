package com.example.garage.service;

import com.example.garage.config.EncoderConfig;
import com.example.garage.exception.CarNotFound;
import com.example.garage.model.Car;
import com.example.garage.model.User;
import com.example.garage.repository.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EncoderConfig encoderConfig;

    @Autowired
    public UserService(UserRepository userRepository, EncoderConfig encoderConfig) {
        this.userRepository = userRepository;
        this.encoderConfig = encoderConfig;
    }

    public Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserByUsername(String userSurname) {
        return userRepository.findUserByUsername(userSurname);
    }

    public User saveUser(User user) {
        user.setPassword(encoderConfig.passwordEncoder().encode(user.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
