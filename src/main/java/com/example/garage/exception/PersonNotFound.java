package com.example.garage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PersonNotFound extends Exception {

    public PersonNotFound(int id) {
        super("Person with id " + id + " not found!", new ResponseStatusException(
                HttpStatus.NOT_FOUND));
    }
}
