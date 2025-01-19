package com.example.garage.controller;

import com.example.garage.model.Client;
import com.example.garage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{clientId}/update")
    public String showClientUpdatingPage(@PathVariable Long clientId) {
        return "UpdateClientPage";
    }

    @PostMapping
    public String createClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return String.format("redirect:/garage/clients/%d/cars", lastCreatedClient());
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/add")
    public String showRegistrationClientPage() {
        return "RegisterClient";
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
}