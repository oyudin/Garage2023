package com.example.garage.repository.dao;

import com.example.garage.model.Car;
import com.example.garage.model.Person;
import com.example.garage.model.Garage;

import java.util.List;

public interface PersonRepository {

    List<Garage> getPersonsWithCars();

    Person getPersonById(int personId);

    List<Person> getAllPersons();

    Person savePerson(Person person);

    Person deletePerson(int id);

    Person updatePerson(Person person, int id);

    List<Car> getListOfPersonCars(int personId);

    Person getTheLastCreatedPerson();
}
