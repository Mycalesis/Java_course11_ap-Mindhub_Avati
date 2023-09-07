package com.ap.homebanking.controllers;
import com.ap.homebanking.dtos.AccountDTO;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.repositories.TransactionRepository;
import com.ap.homebanking.services.AccountService;
import com.ap.homebanking.services.ClientService;
import com.ap.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientService clientService;


    @GetMapping("/clients/current/accounts")
    public List<AccountDTO> getAccountsForCurrentUser(Authentication authentication) {

        Client client03 = clientService.findByEmail(authentication.getName());
        // Verifica si el cliente autenticado es nulo
        if (client03 == null) {
            return (List<AccountDTO>) new ResponseEntity<>("Not authenticated", HttpStatus.UNAUTHORIZED);
        }

        List<AccountDTO> accountDTOs = client03.getAccounts().stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toList());

        return accountDTOs;
    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccountById(@PathVariable Long id) {
        Account account = accountService.findById(id);
        if (account != null) {
            List<Transaction> transactions = transactionService.transactions(account.getId());
            return new AccountDTO(account, new HashSet<>(transactions));
        } else {
            return null;
        }
    }

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> createAccount(Authentication authentication) {
        Client client = clientService.findByEmail(authentication.getName());

        String prefix = "VIN-";
        Random random = new Random();


        for (int attempt = 0; attempt < 100; attempt++) {
            int randomNumber = random.nextInt(99999999) + 1;
            String accountNumber = prefix + randomNumber;


            boolean accountExists = accountService.existsByAccountNumber(accountNumber);

            if (!accountExists) {
                int accountCount = accountService.countByClient(client);

                if (accountCount >= 3) {
                    return new ResponseEntity<>("Cannot create a new account", HttpStatus.FORBIDDEN);
                }

                Account newAccount = new Account(accountNumber, 0, client, LocalDate.now());
                accountService.saveAccounts(newAccount);

                client.addAccount(newAccount);
                return new ResponseEntity<>("Account created correctly", HttpStatus.CREATED);
            }
        }

        return new ResponseEntity<>("Unable to create account", HttpStatus.FORBIDDEN);
    }

}


