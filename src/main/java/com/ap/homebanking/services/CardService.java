package com.ap.homebanking.services;

import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Color;
import com.ap.homebanking.models.TransactionType;

import java.util.List;
import java.util.Optional;

public interface CardService {
    List<Card> sameTypeAndColorCards(Client client, Color color, TransactionType type);

    List<Card> clientCards(Client client);

    Card findByNumber(String number);

    Card cardSave(Card card);

    void deleteCard(Long id);
}
