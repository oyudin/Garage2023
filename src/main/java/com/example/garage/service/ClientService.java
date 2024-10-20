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

    public Optional<Client> getClientById(long id) {
//        System.out.println("!!!!!!!!!!!!!!!!!!!!      " + clientRepository.findById((long)id));
        return clientRepository.findById(Long.valueOf(id));
    }

    public Client saveClient(Client client) {
//        System.out.println(client.getCars());
        return clientRepository.save(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(Long.valueOf(id));
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
