package com.example.garage.service;

import com.example.garage.exception.PersonNotFound;
import com.example.garage.model.Garage;
import com.example.garage.model.Person;
import com.example.garage.repository.dao.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class PersonServiceTest {

    private final PersonRepository personRepository = Mockito.mock(PersonRepository.class);
    private final PersonService personService = new PersonService(personRepository);
    private final Person person = Person.builder().id(1).name("John").surname("Bee").build();

    @Test
    public void getAllPersonAndCarsTest() {
        List<Garage> garageList = new ArrayList<>();
        garageList.add(new Garage(1, "Bob", "Test", 2, "AA1111AI",
                "VW", "Golf", "Black", 1));

        Mockito.when(personRepository.getPersonsWithCars()).thenReturn(garageList);
        List<Garage> garageList2 = personService.getAllPersonsAndCars();
        Assertions.assertEquals(garageList, garageList2);
    }

    @Test
    public void getAllPersonTest() {
        List<Person> personList = new ArrayList<>();
        personList.add(person);

        Mockito.when(personRepository.getAllPersons()).thenReturn(personList);
        List<Person> personList1 = personService.getAllPersons();
        Assertions.assertEquals(personList, personList1);
    }

    @Test
    public void getPersonByIdTest() throws PersonNotFound {
        Mockito.when(personRepository.getPersonById(anyInt())).thenReturn(person);
        Person person1 = personService.getPersonById(new Person().getId());
        Assertions.assertEquals(person, person1);
    }

    @Test
    public void addPersonTest() {
        Mockito.when(personRepository.savePerson(any())).thenReturn(person);
        Person person1 = personService.addPerson(new Person());
        Assertions.assertEquals(person, person1);
    }

    @Test
    public void updatePersonTest() throws PersonNotFound {
        Mockito.when(personRepository.updatePerson(any(), anyInt())).thenReturn(person);
        Person person1 = personService.updatePerson(person, person.getId());
        Assertions.assertEquals(person1, person);
    }

    @Test
    public void deletePersonTest() {
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person);

        Mockito.when(personService.deletePerson(anyInt())).thenReturn(personList.remove(person.getId()));
        Assertions.assertEquals(1, personList.size());
    }
}