package com.ap.homebanking.repositories;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource

public interface ClientRepository extends JpaRepository <Client, Long> {
    //debe ser unico porque es el usuario que inicia sesion
    Client findByEmail(String email);


}