package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Status;
import com.ap.homebanking.models.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {

    private String number;
    private long id;
    private double balance;
    private LocalDate creationDate;
    private List<TransactionDTO> transactions;

    private Status status;

    public AccountDTO(Account account) {
        this.number = account.getNumber();
        this.balance = account.getBalance();
        this.id = account.getId();
        this.creationDate = account.getCreationDate();
        this.transactions = new ArrayList<>(); // Inicializa la lista
        this.status = account.getStatus();
    }

    public AccountDTO(Account account, Set<Transaction> transactions) {
        this(account);
        this.transactions = transactions.stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    public String getNumber() {
        return number;
    }

    //public void setnumber(String number) {this.number = number;}

    public long getId() {
        return id;
    }


    public double getBalance() {
        return balance;
    }

    //public void setBalance(int balance) {this.balance = balance;}

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {this.creationDate = creationDate;}

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public Status getStatus() {
        return status;
    }
}
