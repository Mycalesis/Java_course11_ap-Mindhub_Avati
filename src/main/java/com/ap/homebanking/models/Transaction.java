package com.ap.homebanking.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private double amount;

    private String description;

    private LocalDate date;

    private TransactionType type;


    //Un cliente puede realizar muchas transancciones desde una cuenta pero una transaccion no puede tener muchas cuentas

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    //constructor default

    public Transaction(){}

    //constructor

    public Transaction (double amount, String description, LocalDate date, TransactionType type){
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.type = type;

    }

    //getters and setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

//    public void setDate(LocalDate date) {
//        this.date = date;
//    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
