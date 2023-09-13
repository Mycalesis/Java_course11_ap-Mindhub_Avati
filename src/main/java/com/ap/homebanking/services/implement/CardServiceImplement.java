package com.ap.homebanking.services.implement;

import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Color;
import com.ap.homebanking.models.TransactionType;
import com.ap.homebanking.repositories.CardRepository;
import com.ap.homebanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CardServiceImplement implements CardService {

    @Autowired
    CardRepository cardRepository;
    @Override
    public List<Card> sameTypeAndColorCards(Client client, Color color, TransactionType type) {
        return cardRepository.findByClientAndColorAndType(client, color, type);
    }

    @Override
    public List<Card> clientCards(Client client) {
        return cardRepository.findByClient(client);
    }

    @Override
    public Card findByNumber(String number) {
        return cardRepository.findByNumber(number);
    }

    @Override
    public Card cardSave(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }


}
