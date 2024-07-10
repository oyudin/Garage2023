package com.example.garage.controller;

import com.example.garage.model.User;
import com.example.garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/users")
//    public String getAllUsers(Model model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "Users";
//    }

    @GetMapping("/users")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("users/{username}")
    @Cacheable(value = "users", key = "#username")
    public String getUserByUsername(Model model, @PathVariable String username) {
        model.addAttribute("user", userService.getUserByUsername(username));
        return "User";
    }

    @PutMapping("users")
    public @ResponseBody User saveNewUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("users/{userId}")
    public @ResponseBody void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }
}