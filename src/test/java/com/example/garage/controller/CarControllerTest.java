package com.example.garage.controller;

import com.example.garage.model.Car;
import com.example.garage.repository.dao.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarRepository carRepository;
    @Autowired
    private ObjectMapper objectMapper;
    private final Car testCar = Car.builder().number("AA1234AA").brand("VW").model("Golf").color("Black").build();


    @Test
    public void getAllCarsTest() throws Exception {
        mockMvc.perform(get("/garage/cars")).andExpect(status().is(200)).andReturn();
    }

//    @Test
//    public void saveCarTest() throws Exception {
//        Mockito.when(carRepository.saveCar(any())).thenReturn(testCar);
//        mockMvc.perform(put("/garage/cars")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(testCar)))
//                .andExpect(status().isOk())
//                .andReturn();
//    }

    @Test
    public void updateCarTest() throws Exception {
        Mockito.when(carRepository.updateCar(any(), anyInt())).thenReturn(testCar);
        mockMvc.perform(post("/garage/cars/" + testCar.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCar)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteCarTest() throws Exception {
        Mockito.when(carRepository.deleteCar(anyInt())).thenReturn(testCar);
        mockMvc.perform(delete("/garage/cars/" + anyInt()))
                .andExpect(status().isOk())
                .andReturn();
    }
}
