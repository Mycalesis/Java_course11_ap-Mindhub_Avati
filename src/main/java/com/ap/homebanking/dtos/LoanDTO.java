package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoanDTO {
    private long id;
    private String name;
    private double maxAmount;
    private List<Integer> typeOfPayments = new ArrayList<>();
    private List<ClientLoanDTO> clientLoans = new ArrayList<>();

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.typeOfPayments = loan.getTypeOfPayments();
        this.clientLoans = loan.getClientLoans().stream()
                .map(ClientLoanDTO::new)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getTypeOfPayments() {
        return typeOfPayments;
    }

    public void setTypeOfPayments(List<Integer> typeOfPayments) {
        this.typeOfPayments = typeOfPayments;
    }

    public List<ClientLoanDTO> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(List<ClientLoanDTO> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public void setName(String name) {
        this.name = name;
    }
}

