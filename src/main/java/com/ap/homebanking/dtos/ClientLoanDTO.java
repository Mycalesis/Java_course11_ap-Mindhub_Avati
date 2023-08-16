package com.ap.homebanking.dtos;

import com.ap.homebanking.models.ClientLoan;

public class ClientLoanDTO {

    private long id;
    private LoanDTO loan;
    private ClientDTO client;
    private int payment;
    private double amount;

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.loan = new LoanDTO(clientLoan.getLoan());
        this.client = new ClientDTO(clientLoan.getClient());
        this.payment = clientLoan.getPayment();
        this.amount = clientLoan.getAmount();
    }

    public long getId() {
        return id;
    }

    public LoanDTO getLoans() {
        return loan;
    }

    public void setLoans(LoanDTO loan) {
        this.loan = loan;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
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
