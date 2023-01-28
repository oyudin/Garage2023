package com.example.garage.controller;

import com.example.garage.exception.PersonNotFound;
import com.example.garage.model.Person;
import com.example.garage.service.PersonService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/garage")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getAllPersonsAndCars(Model model) {
        model.addAttribute("personsWithCars", personService.getAllPersonsAndCars());
        return "Garage";
    }

    @GetMapping("/persons")
    public String getAllPersons(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "Persons";
    }

    @GetMapping("persons/{personId}")
    @Cacheable(value = "persons", key = "#personId")
    public @ResponseBody Person getPersonById(@PathVariable int personId) throws PersonNotFound {
        return this.personService.getPersonById(personId);
    }

    @PutMapping("persons")
    public @ResponseBody Person savePerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @DeleteMapping("persons/{personId}")
    public @ResponseBody void deletePerson(@PathVariable int personId) {
        this.personService.deletePerson(personId);
    }


    @PostMapping("persons/{personId}")
    public @ResponseBody void updatePerson(@PathVariable int personId, @RequestBody Person person) throws PersonNotFound {
        this.personService.updatePerson(person, personId);
    }

    @PatchMapping("persons/{personId}/addCar/{carId}")
    public @ResponseBody void addCarToPerson(@PathVariable int personId, @PathVariable int carId) {
        this.personService.addCarToPerson(personId, carId);
    }
}
