package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoanDTO {
    private long id;
    private String name;
    private double maxAmount;
    private List<Integer> payments = new ArrayList<>();
//    private List<ClientLoanDTO> clientLoans = new ArrayList<>();

    public LoanDTO (){}

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments = loan.getTypeOfPayments();
//        this.clientLoans = loan.getClientLoans().stream()
//                .map(ClientLoanDTO::new)
//                .collect(Collectors.toList());
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

    public List<Integer> getPayments() {
        return payments;
    }


//    public List<ClientLoanDTO> getClientLoans() {
//        return clientLoans;
//    }
//
//    public void setClientLoans(List<ClientLoanDTO> clientLoans) {
//        this.clientLoans = clientLoans;
//    }

}

