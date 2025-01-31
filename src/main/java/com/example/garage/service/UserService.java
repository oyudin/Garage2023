package com.example.garage.service;

import com.example.garage.model.User;
import com.example.garage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserByUsername(String userSurname) {
        return userRepository.findUserByUsername(userSurname);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

//    public User updateUser(User user, int userId) {
//        userRepository.updateUser(user, userId);
//        return user;
//    }
}
