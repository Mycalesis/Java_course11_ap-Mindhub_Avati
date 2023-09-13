package com.ap.homebanking.models;



import org.hibernate.query.Query;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String number;
    private double balance;

    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    public Account() {
    }

    public Account(String number, double balance, Client client, LocalDate creationDate) {
        this.number = number;
        this.balance = balance;
        this.client = client;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

//    public void setCreationDate(LocalDate creationDate) {
//        this.creationDate = creationDate;
//    }

    public void addTransaction(Transaction transaction) {
        transaction.setAccount(this);
        transactions.add(transaction);
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }
}
