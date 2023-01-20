package com.example.garage.service;

import com.example.garage.exception.PersonNotFound;
import com.example.garage.model.Person;
import com.example.garage.model.Garage;
import com.example.garage.repository.dao.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Garage> getAllPersonsAndCars() {
        return personRepository.getPersonsWithCars();
    }


    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }


    public Person getPersonById(int personId) throws PersonNotFound {
        Person person = personRepository.getPersonById(personId);
        if (person == null) throw new PersonNotFound(personId);
        return person;
    }


    public Person addPerson(Person person) {
        personRepository.savePerson(person);
        return person;
    }


    public Person updatePerson(Person person, int personId) throws PersonNotFound {
        personRepository.updatePerson(person, personId);
        if (person == null) throw new PersonNotFound(personId);
        return person;
    }


    public Person deletePerson(int id) {
        personRepository.deletePerson(id);
        return null;
    }


    public void addCarToPerson(int personId, int carId) {
        personRepository.addCarToPerson(personId, carId);
    }
}
