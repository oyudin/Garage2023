//package com.example.garage.service;
//
//import com.example.garage.config.EncoderConfig;
//import com.example.garage.model.User;
//import com.example.garage.repository.dao.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import static org.mockito.ArgumentMatchers.any;
//
//public class UserServiceTest {
//    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
//    private final EncoderConfig passwordEncoder = new EncoderConfig();
//    private final User testUser = User.builder()
//            .id(1)
//            .username("TestUser")
//            .password("Password")
//            .role("Test")
//            .build();
//
//    @Test
//    public void saveUserTest() {
//        Mockito.when(userRepository.save(any())).thenReturn(testUser);
//        testUser.setPassword(passwordEncoder.passwordEncoder().encode(testUser.getPassword()));
//        testUser.setRole("USER");
//        Assertions.assertNotEquals(testUser.getPassword(), "Password");
//        Assertions.assertEquals(testUser.getRole(), "USER");
//    }
//}