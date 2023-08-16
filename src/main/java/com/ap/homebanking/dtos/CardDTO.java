package com.ap.homebanking.dtos;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Color;
import com.ap.homebanking.models.TransactionType;
import java.time.LocalDate;

public class CardDTO {
    private long id;

    private TransactionType type;

    private String cardHolder;

    private Color cardColor;

    private String cardNumber;

    private int cvv;

    private LocalDate creationDate;
    private LocalDate expirationDate;

    public CardDTO(Card card) {

        this.id = card.getId();
        this.type = card.getType();
        this.cardHolder = card.getCardHolder();
        this.cardColor = card.getCardColor();
        this.cvv = card.getCvv();
        this.cardNumber = card.getCardNumber();
        this.creationDate = card.getCreationDate();
        this.expirationDate = card.getExpirationDate();


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
}
