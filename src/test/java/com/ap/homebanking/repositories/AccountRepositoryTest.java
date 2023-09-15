package com.ap.homebanking.repositories;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testCountByClient() {





        Client melba = new Client("Melba", "Morel", "melbamorel@mindhub.com", "melba123", "Client");
        Account account = new Account("VIN-00000001", 5000, melba, LocalDate.now(), Status.ACTIVE);
        accountRepository.save(account);

        int count = accountRepository.countByClient(melba);


        assertThat(count).isEqualTo(1);
    }

    @Test
    public void testFindByClientAndNumber() {

        Client melba = new Client("Melba", "Morel", "melbamorel@mindhub.com", "melba123", "Client");
        Account account = new Account("VIN-00000001", 5000, melba, LocalDate.now(), Status.ACTIVE);
        accountRepository.save(account);


        Account foundAccount = accountRepository.findByClientAndNumber(melba, "VIN-00000001");


        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount.getNumber()).isEqualTo("VIN-00000001");
    }

    @Test
    public void testFindByNumber() {

        Client melba = new Client("Melba", "Morel", "melbamorel@mindhub.com", "melba123", "Client");
        Account account = new Account( "VIN-00000001", 5000, melba, LocalDate.now(), Status.ACTIVE);
        accountRepository.save(account);

        Account foundAccount = accountRepository.findByNumber("VIN-00000001");


        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount.getNumber()).isEqualTo("VIN-00000001");
    }

    @Test
    public void testExistsByNumber() {
        Client melba = new Client("Melba", "Morel", "melbamorel@mindhub.com", "melba123", "Client");
        Account account = new Account( "VIN-00000001", 5000, melba, LocalDate.now(), Status.ACTIVE);
        accountRepository.save(account);


        boolean exists = accountRepository.existsByNumber("VIN-00000001");

        assertThat(exists).isTrue();
    }
}