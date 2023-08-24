package com.ap.homebanking.dtos;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Color;
import com.ap.homebanking.models.TransactionType;
import java.time.LocalDate;

public class CardDTO {
    private long id;

    private TransactionType type;

    private String cardHolder;

    private Color color;

    private String number;

    private int cvv;

    private LocalDate creationDate;
    private LocalDate thruDate;

    public CardDTO(Card card) {

        this.id = card.getId();
        this.type = card.getType();
        this.cardHolder = card.getCardHolder();
        this.color = card.getColor();
        this.cvv = card.getCvv();
        this.number = card.getNumber();
        this.creationDate = card.getCreationDate();
        this.thruDate = card.getThruDate();


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

    public Color getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public LocalDate getThruDate() {
        return thruDate;
    }

}
