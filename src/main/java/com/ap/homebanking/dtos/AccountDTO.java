package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {

    private String accountNumber;
    private long id;
    private int balance;
    private LocalDate creationDate;
    private List<TransactionDTO> transactions;

    public AccountDTO(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getBalance();
        this.id = account.getId();
        this.creationDate = account.getCreationDate();
        this.transactions = new ArrayList<>(); // Inicializa la lista
    }

    public AccountDTO(Account account, Set<Transaction> transactions) {
        this(account);
        this.transactions = transactions.stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }


}
