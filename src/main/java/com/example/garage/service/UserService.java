package com.example.garage.service;

import com.example.garage.model.User;
import com.example.garage.repository.dao.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserByUsername(String userSurname) {
        return userRepository.findUserByUsername(userSurname);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

//    public User updateUser(User user, int userId) {
//        userRepository.updateUser(user, userId);
//        return user;
//    }
}
