package com.ap.homebanking.controllers;
import com.ap.homebanking.dtos.CardDTO;
import com.ap.homebanking.dtos.ClientDTO;
import com.ap.homebanking.models.*;
import com.ap.homebanking.services.CardService;
import com.ap.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CardService cardService;

    @GetMapping("/clients/current/cards")
    public ResponseEntity<Set<CardDTO>> filterCards(
            Authentication authentication,
            @RequestParam Color color,
            @RequestParam TransactionType type) {

        String email = authentication.getName();
        Client client = clientService.findByEmail(email);
        ClientDTO clientDTO = new ClientDTO(client);

        // Filter for status
        Set<Card> activeCards = client.getCards().stream()
                .filter(card -> card.getStatus().equals(Status.ACTIVE))
                .collect(Collectors.toSet());
        System.out.println(activeCards);
        // Filter for color & typr
        Set<CardDTO> filteredCards = activeCards.stream()
                .filter(card -> card.getColor() == color && card.getType() == type)
                .map(card -> new CardDTO(card))
                .collect(Collectors.toSet());

        return ResponseEntity.ok(filteredCards);
    }

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(
            @RequestParam Color cardColor,
            @RequestParam TransactionType cardType,
            Authentication authentication) {

        Client authClient = clientService.findByEmail(authentication.getName());

        List<Card> sameTypeAndColorCards = cardService.sameTypeAndColorCards(authClient, cardColor, cardType);

        if (sameTypeAndColorCards.size() >= 1) {
            return new ResponseEntity<>("Error, a card of this type and color already exists", HttpStatus.FORBIDDEN);
        }

        List<Card> clientCards = cardService.clientCards(authClient);

        if (clientCards.size() >= 6) {
            return new ResponseEntity<>("Error, max number of cards surpassed", HttpStatus.FORBIDDEN);
        }

        ////
        Random random = new Random();
        int cvv = random.nextInt(999) + 1;
        int numberCard = random.nextInt(9999) + 1;
        int numberCard2 = random.nextInt(9999) + 1;
        int numberCard3 = random.nextInt(9999) + 1;
        int numberCard4 = random.nextInt(9999) + 1;
        String randomCard = numberCard + "-" + numberCard2 + "-" + numberCard3 + "-" + numberCard4;
        LocalDate thruDate = LocalDate.now().plus(5, ChronoUnit.YEARS);
        String cardHolder = authClient.getFirstName() + " " + authClient.getLastName();
        /////

        Card newCard = new Card(cardType, cardHolder, cardColor, randomCard, cvv, LocalDate.now(), thruDate, Status.ACTIVE);
        newCard.setClient(authClient);
        cardService.cardSave(newCard);

        return ResponseEntity.ok("Card created successfully");
    }


    @PostMapping("/cards")
    public ResponseEntity<String> deleteCard(
            @RequestParam String number,
            Authentication authentication) {
        System.out.println(number);
        Client authClient = clientService.findByEmail(authentication.getName());

        Card cardFound = cardService.findByNumber(number);
        System.out.println(cardFound.getStatus());
        if (cardFound == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Card missing");
        }

        if (cardFound.getStatus() == Status.ACTIVE) {

            cardFound.setStatus(Status.INACTIVE);
            cardService.cardSave(cardFound);
            cardFound.setClient(authClient);
            System.out.println(cardFound.getStatus());
            return ResponseEntity.ok("Card deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found or not active");
        }

    }
}
