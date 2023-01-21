package com.example.garage.controller;

import com.example.garage.model.Person;
import com.example.garage.repository.dao.PersonRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonRepository personRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private final Person testPerson = Person.builder().name("Test").surname("Test").build();

    @Test
    public void getAllPersonsAndCars() throws Exception {
        mockMvc.perform(get("/garage")).andExpect(status().isOk()).andReturn();

    }

    @Test
    public void getAllPersons() throws Exception {
        mockMvc.perform(get("/garage/persons")).andExpect(status().isOk()).andReturn();

    }

    @Test
    public void getPersonByIdTest() throws Exception {
        Mockito.when(personRepository.getPersonById(anyInt())).thenReturn(testPerson);
        mockMvc.perform(get("/garage/persons/" + anyInt()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void savePersonTest() throws Exception {
        Mockito.when(personRepository.savePerson(any())).thenReturn(testPerson);
        mockMvc.perform(put("/garage/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPerson)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deletePersonTest() throws Exception {
        Mockito.when(personRepository.deletePerson(anyInt())).thenReturn(testPerson);
        mockMvc.perform(delete("/garage/persons/" + anyInt()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updatePersonTest() throws Exception {
        Mockito.when(personRepository.updatePerson(any(), anyInt())).thenReturn(testPerson);
        mockMvc.perform(post("/garage/persons/" + testPerson.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPerson)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addCarToPersonTest() throws Exception {
        Mockito.when(personRepository.addCarToPerson(anyInt(), anyInt())).thenReturn(testPerson);
        mockMvc.perform(post("/garage/persons/" + testPerson.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPerson)))
                .andExpect(status().isOk())
                .andReturn();
    }
}