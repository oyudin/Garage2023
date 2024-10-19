//package com.example.garage.controller;
//
//import com.example.garage.model.User;
//import com.example.garage.repository.dao.UserRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("dev")
//public class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private UserRepository userRepository;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private final User testUser = User.builder().id(1).username("Test").password("Password").role("USER").build();
//
//    @Test
//    public void getAllUsersTest() throws Exception {
//        mockMvc.perform(get("/users")).andExpect(status().isOk()).andReturn();
//
//    }
//
//    @Test
//    public void getUserByUsernameTest() throws Exception {
//        Mockito.when(userRepository.findUserByUsername(any())).thenReturn(testUser);
//        mockMvc.perform(get("/users/" + any()))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @Test
//    public void saveNewUserTest() throws Exception {
//        Mockito.when(userRepository.save(any())).thenReturn(testUser);
//        mockMvc.perform(put("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(testUser)))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @Test
//    public void deleteUserTest() throws Exception {
//        mockMvc.perform(delete("/users/" + testUser.getId()))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//}