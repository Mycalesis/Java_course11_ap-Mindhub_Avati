package com.ap.homebanking.repositories;

import com.ap.homebanking.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Test
    void findByClient() {
        Client melba = new Client("Melba", "Morel", "melbamorel@mindhub.com", "melba123", "Client");
        Card card02 = new Card(TransactionType.CREDIT, "Melba Morel", Color.TITANIUM, "7273-4782-2273-9754", 275, LocalDate.now(),LocalDate.now().plus(5, ChronoUnit.YEARS), Status.ACTIVE );
        melba.addCard(card02);

        List<Card> cardsByClient = cardRepository.findByClient(melba);


        assertNotNull(cardsByClient);
        assertEquals(2, cardsByClient.size());
    }


    @Test
    void findByNumber() {
        Client melba = new Client("Melba", "Morel", "melbamorel@mindhub.com", "melba123", "Client");
        Card card02 = new Card(TransactionType.CREDIT, "Melba Morel", Color.TITANIUM, "7273-4782-2273-9754", 275, LocalDate.now(),LocalDate.now().plus(5, ChronoUnit.YEARS), Status.ACTIVE );
        melba.addCard(card02);

        List<Card> cardsByClient = cardRepository.findByClient(melba);

        // Buscar la tarjeta por número
        Card foundCard = cardRepository.findByNumber("7273-4782-2273-9754");

        // Verificar que se haya encontrado la tarjeta
        assertNotNull(foundCard);
        assertEquals("7273-4782-2273-9754", foundCard.getNumber());
    }

    }



