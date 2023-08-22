package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.ClientDTO;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Rol;
import com.ap.homebanking.repositories.CardRepository;
import com.ap.homebanking.repositories.ClientLoanRepository;
import com.ap.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {


    @Autowired
    private  ClientRepository clientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    // Endpoint para registrar un nuevo cliente
    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Rol rol) {

        // Verificar si faltan datos
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.BAD_REQUEST);
        }

        // Verificar si el correo ya está en uso
        if (clientRepository.findByEmail(email) != null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.BAD_REQUEST);
        }

        // Crear y guardar el nuevo cliente
        clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password), rol));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Endpoint para obtener la lista de clientes
    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    // Endpoint para obtener un cliente por su ID
    @RequestMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

