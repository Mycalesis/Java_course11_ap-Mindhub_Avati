package com.ap.homebanking.dtos;
import com.ap.homebanking.models.ClientLoan;

public class LoanApplicationDTO {

    private long loanId;
    private double maxAmount;
    private int payments;

    private String nameLoan;

    private String toAccountNumber;


    public long getLoanId() {
        return loanId;
    }


    public double getMaxAmount() {
        return maxAmount;
    }

    public int getPayments() {
        return payments;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public String getNameLoan() {
        return nameLoan;
    }
}
