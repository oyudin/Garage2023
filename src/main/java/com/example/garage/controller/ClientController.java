package com.example.garage.controller;

import com.example.garage.model.Client;
import com.example.garage.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/carservice/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    private Long lastCreatedClient() {
        return clientService.getLastClient().getId();
    }

    @GetMapping("/{clientId}/update")
    public String showClientUpdatingPage(@PathVariable Long clientId) {
        return "UpdateClientPage";
    }

    @GetMapping("/add")
    public String showRegistrationClientPage() {
        return "RegisterClient";
    }

    @GetMapping()
    public String getAllClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "Client";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Client> getAllClientsJson() {
        return clientService.getAllClients();
    }

    @GetMapping("/{clientId}")
    @ResponseBody
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
        Optional<Client> clientById = clientService.getClientById(clientId);
        return clientById.map(client -> ResponseEntity.ok().body(client))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public String searchClient(@RequestParam(name = "search", required = false) String searchTerm, Model model) {
        List<Client> clients;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            clients = clientService.searchClients(searchTerm);
        } else {
            clients = clientService.getAllClients();
        }
        model.addAttribute("clients", clients);
        return "Client";
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client newClient = clientService.saveClient(client);

        if (newClient != null) {
            clientService.saveClient(client);
//            String.format("redirect:/carservice/clients/%d/cars", lastCreatedClient());
            return ResponseEntity.status(201).body(client);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{clientId}/update")
    @ResponseBody
    public ResponseEntity<Client> updateClient(@PathVariable Long clientId, @RequestBody Client client) {
        Optional<Client> updatedClient = clientService.getClientById(clientId);

        if (updatedClient.isPresent()) {
            clientService.updateClient(clientId, client);
            return ResponseEntity.ok().body(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public String deleteClient(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);

        if (client.isPresent()) {
            clientService.deleteClient(id);
            ResponseEntity.ok().body(client.get());
        } else {
            log.error("Error. The client has NOT been deleted");
            ResponseEntity.notFound().build();
        }
        return "redirect:/carservice/clients";
    }
}