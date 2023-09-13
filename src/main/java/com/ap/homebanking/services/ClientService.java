package com.ap.homebanking.services;

import com.ap.homebanking.dtos.ClientDTO;
import com.ap.homebanking.models.Client;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getClients();

    Client findById(long id);

    Client findByEmail(String email);

    Client savedClient(Client client);


}
