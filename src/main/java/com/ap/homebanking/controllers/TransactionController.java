package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.TransactionDTO;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.models.TransactionType;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.repositories.ClientRepository;
import com.ap.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions() {
        return transactionRepository.findAll().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toList());
    }

    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> createTransaction(
            @RequestParam String fromAccountNumber,
            @RequestParam String toAccountNumber,
            @RequestParam double amount,
            @RequestParam String description,
            Authentication authentication) {

        if (amount <= 0 || description.isBlank() || fromAccountNumber.isBlank() || toAccountNumber.isBlank()) {
            return new ResponseEntity<>("Missing or invalid data", HttpStatus.BAD_REQUEST);
        }

        Account clientAccountsTo = accountRepository.findByNumber(toAccountNumber);

        if (clientAccountsTo == null) {
            return new ResponseEntity<>("Missing or invalid data", HttpStatus.FORBIDDEN);
        }
        Client clientAuth = clientRepository.findByEmail(authentication.getName());

        Account clientAccounts = accountRepository.findByClientAndNumber(clientAuth, fromAccountNumber);

        if (clientAccounts == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (clientAccounts.getBalance() == 0){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // Comparing account numbers
        if (fromAccountNumber.equals(toAccountNumber)) {
            return new ResponseEntity<>("Source and destination accounts must be different", HttpStatus.FORBIDDEN);
        }

        Transaction debitTransaction = new Transaction(amount, description, LocalDate.now(), TransactionType.DEBIT);
        Transaction creditTransaction = new Transaction(amount, description, LocalDate.now(), TransactionType.CREDIT);

        clientAccounts.addTransaction(debitTransaction);
        clientAccountsTo.addTransaction(creditTransaction);

        debitTransaction.setAccount(clientAccounts);
        creditTransaction.setAccount(clientAccountsTo);

        clientAccounts.setBalance(clientAccounts.getBalance() - amount);
        clientAccountsTo.setBalance(clientAccountsTo.getBalance() + amount);

        transactionRepository.save(debitTransaction);
        transactionRepository.save(creditTransaction);
        accountRepository.save(clientAccounts);
        accountRepository.save(clientAccountsTo);
        return new ResponseEntity<>("Transaction successful", HttpStatus.CREATED);
    }
}
