package com.example.garage.controller;

import com.example.garage.exception.PersonNotFound;
import com.example.garage.model.Person;
import com.example.garage.service.CarService;
import com.example.garage.service.PersonService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/garage")
public class PersonController {

    private final PersonService personService;
    private final CarService carService;

    public PersonController(PersonService personService, CarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    private int lastCreatedPerson(){
        System.out.println(personService.getTheLastCreatedPerson().getId());
        return personService.getTheLastCreatedPerson().getId();
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


//    @PostMapping("persons")
//    public Person savePerson(@ModelAttribute Person person) {
//        return personService.addPerson(person);
//    }

    @PostMapping("persons")
    public String savePerson(@ModelAttribute Person person) {
        personService.addPerson(person);
        return "redirect:/garage/persons/" + lastCreatedPerson() + "/cars";
    }

    @GetMapping("persons/registration")
    public String showRegistrationClientPage() {
        return "RegisterClient";
    }


    @DeleteMapping("persons/{personId}")
    public @ResponseBody void deletePerson(@PathVariable int personId) {
        this.personService.deletePerson(personId);
    }


//    @PostMapping("persons/{personId}")
//    public @ResponseBody void updatePerson(@PathVariable int personId, @RequestBody Person person) throws PersonNotFound {
//        this.personService.updatePerson(person, personId);
//    }


    @GetMapping("persons/{personId}/cars")
    public String findCarsByPerson(Model model, @PathVariable int personId) {
        model.addAttribute("carByPerson", personService.getListOfPersonCars(personId));
        return "PersonCars";
    }


    //    @PostMapping("/persons/{personId}/addCar")
//    public @ResponseBody void createCarToPerson(@PathVariable int personId, @RequestBody Car car) {
//        carService.addCar(personId, car);
//    }


}
