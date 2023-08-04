package com.ap.homebanking;

import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Tester implements CommandLineRunner {

    private final ClientRepository clientRepository;

    @Autowired
    public Tester(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Client client = new Client("Melba", "Morel", "melba@mindhub.com");
        clientRepository.save(client);
    }
}