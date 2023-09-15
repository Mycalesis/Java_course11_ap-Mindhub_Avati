package com.ap.homebanking.controllers;
import com.ap.homebanking.dtos.ClientDTO;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Status;
import com.ap.homebanking.services.AccountService;
import com.ap.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;




@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AccountService accountService;

    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientService.getClients();
    }


    @GetMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        if (client != null) {
            return new ClientDTO(client);
        } else {
            return null;
        }
    }



    @PostMapping("/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.BAD_REQUEST);
        }

        if (clientService.findByEmail(email) != null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.BAD_REQUEST);
        }

        Client newClient = new Client(firstName, lastName, email, passwordEncoder.encode(password), "CLIENT");
        Client savedClient = clientService.savedClient(newClient);

        String prefix = "VIN-";
        Random random = new Random();
        int randomNumber = random.nextInt(99999999) + 1;
        String accountNumber = prefix + randomNumber;

        Account newAccount = new Account(accountNumber, 0, savedClient, LocalDate.now(), Status.ACTIVE);
        accountService.saveAccounts(newAccount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/clients/current")
    public ResponseEntity<ClientDTO> getCurrentClient(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Unauthorized if not authenticated
        }
        String email = authentication.getName();
        Client client2 = clientService.findByEmail(email);

        if (client2 != null) {
            ClientDTO clientDTO = new ClientDTO(client2);
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
