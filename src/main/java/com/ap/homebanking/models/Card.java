package com.ap.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;


@Entity

public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private TransactionType type;

    private String cardHolder;

    private Color cardColor;

    private String cardNumber;

    private int cvv;

    private LocalDate creationDate;
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    //constructor default
    public Card(){}
    public Card(TransactionType type, String cardHolder, Color cardColor, String cardNumber, int cvv, LocalDate creationDate, LocalDate expirationDate) {
    this.type = type;
    this.cardHolder = cardHolder;
    this.cardColor = cardColor;
    this.cardNumber = cardNumber;
    this.cvv = cvv;
    this.creationDate =creationDate;
    this.expirationDate =expirationDate;

}

    public long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Color getCardColor() {
        return cardColor;
    }

    public void setCardColor(Color cardColor) {
        this.cardColor = cardColor;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
