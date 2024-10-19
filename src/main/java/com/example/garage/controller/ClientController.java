package com.example.garage.controller;

import com.example.garage.model.Client;
import com.example.garage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/garage/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    private Long lastCreatedClient() {
        return clientService.getLastClient().getId();
    }

    @GetMapping()
    public String getAllClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "Client";
    }

    @GetMapping("/{id}")
    public String getClientById(Model model, @PathVariable int id) {
        model.addAttribute("carByClient", clientService.getClientById(id));
        return "ClientCars";
    }

    @PostMapping
    public String createClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/garage/clients/" + lastCreatedClient();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/registration")
    public String showRegistrationClientPage() {
        return "RegisterClient";
    }

    @GetMapping("/") // Убедитесь, что это совпадает с вашим формой
    public String searchClient(@RequestParam(name = "search", required = false) String searchTerm, Model model) {
        List<Client> clients;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            clients = clientService.searchClients(searchTerm); // Ваш метод для выполнения поиска
        } else {
            clients = clientService.getAllClients(); // Ваш метод для получения всех персон
        }
        System.out.println(clients);
        model.addAttribute("clients", clients);
        return "Client"; // Возвращаем имя шаблона Thymeleaf для отображения результатов поиска
    }
}