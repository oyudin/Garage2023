//package com.example.garage.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//
//    private final UserService userService;
//
//    @Autowired
//    public CustomUserDetailsService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        com.example.garage.model.User user = userService.getUserByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .authorities(user.getRole())
//                .build();
//    }
//}
