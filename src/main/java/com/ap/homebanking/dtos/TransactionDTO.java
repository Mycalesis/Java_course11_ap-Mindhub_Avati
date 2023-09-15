package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Status;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.models.TransactionType;

import java.time.LocalDate;

public class TransactionDTO {

    private long id;

    private double amount;

    private String description;

    private LocalDate date;

    private TransactionType type;

    private double total;

    private Status status;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.total = transaction.getTotal();
        this.status = transaction.getStatus();

    }

    public double getTotal() {
        return total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}


