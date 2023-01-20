package com.example.garage.repository;

import com.example.garage.model.Person;
import com.example.garage.model.Garage;
import com.example.garage.repository.dao.PersonRepository;
import com.example.garage.repository.mapper.GarageMapper;
import com.example.garage.repository.mapper.PersonMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.garage.repository.SQLQueries.*;

@Repository
public class PersonImpl implements PersonRepository {

    private final JdbcTemplate jdbcTemplate;

    public PersonImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Garage> getPersonsWithCars() {
        return jdbcTemplate.query(SELECT_ALL_PERSONS_WITH_CARS.query, new GarageMapper());
    }

    @Override
    public List<Person> getAllPersons() {
        return jdbcTemplate.query(SELECT_ALL_PERSONS.query, new PersonMapper());
    }

    @Override
    public Person getPersonById(int personId) {
        return jdbcTemplate.queryForObject(SELECT_PERSON_BY_ID.query + personId, new PersonMapper());
    }

    @Override
    public Person savePerson(Person person) {
        jdbcTemplate.update(SAVE_PERSON.query, person.getName(), person.getSurname());
        return person;
    }

    @Override
    public Person deletePerson(int personId) {
        this.jdbcTemplate.execute(DELETE_PERSON.query + personId);
        return null;
    }


    @Override
    public Person updatePerson(Person person, int personId) {
        jdbcTemplate.update(UPDATE_PERSON.query + personId, person.getName(), person.getSurname());
        return person;
    }

    @Override
    public Person addCarToPerson(int personId, int carId) {
        jdbcTemplate.update(ADD_CAR_TO_PERSON.query, personId, carId);
        return null;
    }
}
