package com.ap.homebanking.dtos;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Color;
import com.ap.homebanking.models.Status;
import com.ap.homebanking.models.TransactionType;
import java.time.LocalDate;

public class CardDTO {
    private long id;

    private TransactionType type;

    private String cardHolder;

    private Color color;

    private String number;

    private Status status;

    private int cvv;

    private LocalDate creationDate;
    private LocalDate thruDate;

    public CardDTO() {
    }

    public CardDTO(Card card) {

        this.id = card.getId();
        this.type = card.getType();
        this.cardHolder = card.getCardHolder();
        this.color = card.getColor();
        this.cvv = card.getCvv();
        this.number = card.getNumber();
        this.creationDate = card.getCreationDate();
        this.thruDate = card.getThruDate();
        this.status = card.getStatus();


    }

    public Status getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }


    public String getCardHolder() {
        return cardHolder;
    }

    public Color getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }


    public int getCvv() {
        return cvv;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

}
