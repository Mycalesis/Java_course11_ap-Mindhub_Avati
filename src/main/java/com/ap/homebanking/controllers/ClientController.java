package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.ClientDTO;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.ClientLoan;
import com.ap.homebanking.repositories.ClientRepository;
import com.ap.homebanking.repositories.ClientLoanRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientRepository clientRepository;
    private final ClientLoanRepository clientLoanRepository;

    public ClientController(ClientRepository clientRepository, ClientLoanRepository clientLoanRepository) {
        this.clientRepository = clientRepository;
        this.clientLoanRepository = clientLoanRepository;
    }

    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            List<ClientLoan> clientLoans = clientLoanRepository.findByClientId(client.getId());
            return new ClientDTO(client, new HashSet<>(clientLoans));
        } else {
            return null;
        }
    }
}

