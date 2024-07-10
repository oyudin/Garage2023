package com.example.garage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CarNotFound extends Exception {

    public CarNotFound(int id) {
        super("Car with id " + id + " not found!", new ResponseStatusException(
                HttpStatus.NOT_FOUND));
    }
}
