package com.example.garage.service;

import com.example.garage.model.Client;
import com.example.garage.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> updateClient(Long clientId, Client client) {
        Optional<Client> updatedClient = getClientById(clientId);

        if (updatedClient.isPresent()) {
            updatedClient.get().setName(client.getName());
            updatedClient.get().setSurname(client.getSurname());
            updatedClient.get().setPhoneNumber(client.getPhoneNumber());

            saveClient(updatedClient.get());
            return updatedClient;
        } else {
            return Optional.empty();
        }
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Client getLastClient() {
        List<Client> theListOfClients = getAllClients();
        return theListOfClients.get(theListOfClients.size() - 1);
    }

    public List<Client> searchClients(String searchParameter) {
        List<Client> theListOfClients = getAllClients();
        String lowerCaseSearchParam = searchParameter.toLowerCase();

        return theListOfClients.stream()
                .filter(client -> client.getName().toLowerCase().contains(lowerCaseSearchParam) ||
                        client.getSurname().toLowerCase().contains(lowerCaseSearchParam))
                .collect(Collectors.toList());
    }
}
