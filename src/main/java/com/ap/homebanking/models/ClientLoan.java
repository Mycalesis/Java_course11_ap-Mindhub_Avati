package com.ap.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    private String nameLoan;

    private int payment;

    private double amount;

    public ClientLoan() {
    }

    public ClientLoan(Loan loan, String nameLoan, int payment, double amount, Client client) {
        this.loan = loan;
        this.nameLoan = nameLoan;
        this.client = client;
        this.payment = payment;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public String getNameLoan() {
        return nameLoan;
    }

    public void setNameLoan(String nameLoan) {
        this.nameLoan = nameLoan;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}