package com.ap.homebanking.services.implement;

import com.ap.homebanking.dtos.ClientDTO;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.ClientRepository;
import com.ap.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @Override
    public Client findById(long id) {
       return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
    @Override
    public Client savedClient(Client client) {
        return clientRepository.save(client);
    }
}
