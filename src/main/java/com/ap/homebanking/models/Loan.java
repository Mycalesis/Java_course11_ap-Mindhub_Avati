package com.ap.homebanking.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//tipo de prestamo que puede existir y puedo tomar



@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")

    private long id;

    private String name;
    private double maxAmount;

    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    @ElementCollection
    @Column(name = "typeOfpayment")
    private List<Integer> typeOfPayments = new ArrayList<>();

    //default
    public Loan(){}
    public Loan(String name, double maxAmount, List<Integer> typeOfPayments) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.typeOfPayments = typeOfPayments;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public List<Integer> getTypeOfPayments() {
        return typeOfPayments;
    }

    public void setTypeOfPayments(List<Integer> typeOfPayments) {
        this.typeOfPayments = typeOfPayments;
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }
}
