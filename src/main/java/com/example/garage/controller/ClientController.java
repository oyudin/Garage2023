package com.example.garage.controller;

import com.example.garage.exception.PersonNotFound;
import com.example.garage.model.Person;
import com.example.garage.service.CarService;
import com.example.garage.service.ClientService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/garage")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    private int lastCreatedPerson() {
        System.out.println(clientService.getTheLastCreatedPerson().getId());
        return clientService.getTheLastCreatedPerson().getId();
    }

    @GetMapping("/persons")
    public String searchPersons(@RequestParam(name = "search", required = false) String searchTerm, Model model) {
        List<Person> persons;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            persons = clientService.searchByPersonName(searchTerm); // Ваш метод для выполнения поиска
        } else {
            persons = clientService.getAllPersons(); // Ваш метод для получения всех персон
        }
        model.addAttribute("persons", persons);
        return "Client"; // Возвращаем имя шаблона Thymeleaf для отображения результатов поиска
    }


    @GetMapping
    public String getAllPersonsAndCars(Model model) {
        model.addAttribute("personsWithCars", clientService.getAllPersonsAndCars());
        return "Garage";
    }


    @GetMapping("persons/{personId}/cars")
    public String findCarsByPerson(Model model, @PathVariable int personId) {
        model.addAttribute("carByPerson", clientService.getListOfPersonCars(personId));
        return "ClientCars";
    }

    @GetMapping("persons/{personId}")
    @Cacheable(value = "persons", key = "#personId")
    public @ResponseBody Person getPersonById(@PathVariable int personId) throws PersonNotFound {
        return this.clientService.getPersonById(personId);
    }


    @GetMapping("persons/registration")
    public String showRegistrationClientPage() {
        return "RegisterClient";
    }

    @PostMapping("persons")
    public String savePerson(@ModelAttribute Person person) {
        clientService.addPerson(person);
        return "redirect:/garage/persons/" + lastCreatedPerson() + "/cars";
    }

    @DeleteMapping("persons/{personId}")
    public @ResponseBody void deletePerson(@PathVariable int personId) {
        this.clientService.deletePerson(personId);
    }
}
