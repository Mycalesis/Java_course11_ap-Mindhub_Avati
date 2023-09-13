package com.ap.homebanking.repositories;


import com.ap.homebanking.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long>
{
    List<Card> findByClient(Client client);

    Card findByNumber(String number);
    List<Card> findByClientAndColorAndType(Client authClient, Color cardColor, TransactionType cardType);
}
