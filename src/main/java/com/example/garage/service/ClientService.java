package com.example.garage.service;

import com.example.garage.exception.PersonNotFound;
import com.example.garage.model.Car;
import com.example.garage.model.Person;
import com.example.garage.model.Garage;
import com.example.garage.repository.dao.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Garage> getAllPersonsAndCars() {
        return clientRepository.getPersonsWithCars();
    }


    public List<Person> getAllPersons() {
        return clientRepository.getAllPersons();
    }


    public Person getPersonById(int personId) throws PersonNotFound {
        Person person = clientRepository.getPersonById(personId);
        if (person == null) throw new PersonNotFound(personId);
        return person;
    }


    public Person addPerson(Person person) {
        clientRepository.savePerson(person);
        return person;
    }


    public Person updatePerson(Person person, int personId) throws PersonNotFound {
        clientRepository.updatePerson(person, personId);
        if (person == null) throw new PersonNotFound(personId);
        return person;
    }

    public Person deletePerson(int id) {
        clientRepository.deletePerson(id);
        return null;
    }


    public List<Car> getListOfPersonCars(int personId) {
        return clientRepository.getListOfPersonCars(personId);
    }

    public Person getTheLastCreatedPerson() {
        return clientRepository.getTheLastCreatedPerson();
    }

    public List<Person> searchByPersonName(String personName) {
        return clientRepository.searchPersonByName(personName);
    }
}
