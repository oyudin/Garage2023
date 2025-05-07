//package com.example.garage.service;
//
//import com.example.garage.exception.PersonNotFound;
//import com.example.garage.model.Garage;
//import com.example.garage.model.Person;
//import com.example.garage.repository.dao.ClientRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//
//public class ClientServiceTest {
//
//    private final ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
//    private final ClientService clientService = new ClientService(clientRepository);
//    private final Person person = Person.builder().id(1).name("John").surname("Bee").build();
//
//    @Test
//    public void getAllPersonAndCarsTest() {
//        List<Garage> garageList = new ArrayList<>();
//        garageList.add(new Garage(1, "Bob", "Test", 2, "AA1111AI",
//                "VW", "Golf", "Black", 1));
//
//        Mockito.when(clientRepository.getPersonsWithCars()).thenReturn(garageList);
//        List<Garage> garageList2 = clientService.getAllPersonsAndCars();
//        Assertions.assertEquals(garageList, garageList2);
//    }
//
//    @Test
//    public void getAllPersonTest() {
//        List<Person> personList = new ArrayList<>();
//        personList.add(person);
//
//        Mockito.when(clientRepository.getAllPersons()).thenReturn(personList);
//        List<Person> personList1 = clientService.getAllPersons();
//        Assertions.assertEquals(personList, personList1);
//    }
//
//    @Test
//    public void getPersonByIdTest() throws PersonNotFound {
//        Mockito.when(clientRepository.getPersonById(anyInt())).thenReturn(person);
//        Person person1 = clientService.getPersonById(new Person().getId());
//        Assertions.assertEquals(person, person1);
//    }
//
//    @Test
//    public void addPersonTest() {
//        Mockito.when(clientRepository.savePerson(any())).thenReturn(person);
//        Person person1 = clientService.addPerson(new Person());
//        Assertions.assertEquals(person, person1);
//    }
//
//    @Test
//    public void updatePersonTest() throws PersonNotFound {
//        Mockito.when(clientRepository.updatePerson(any(), anyInt())).thenReturn(person);
//        Person person1 = clientService.updatePerson(person, person.getId());
//        Assertions.assertEquals(person1, person);
//    }
//
//    @Test
//    public void deletePersonTest() {
//        List<Person> personList = new ArrayList<>();
//        personList.add(person);
//        personList.add(person);
//
//        Mockito.when(clientService.deletePerson(anyInt())).thenReturn(personList.remove(person.getId()));
//        Assertions.assertEquals(1, personList.size());
//    }
//}