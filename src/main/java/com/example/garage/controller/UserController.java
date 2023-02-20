package com.example.garage.controller;

import com.example.garage.model.User;
import com.example.garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "Users";
    }

    @GetMapping("/{username}")
    @Cacheable(value = "users", key = "#username")
    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping
    public User saveNewUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{userId}")
    public @ResponseBody void deleteUser(@PathVariable int userId) {
        this.userService.deleteUser(userId);
    }
}