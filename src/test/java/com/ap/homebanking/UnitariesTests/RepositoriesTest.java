package com.ap.homebanking.UnitariesTests;

import com.ap.homebanking.models.*;
import com.ap.homebanking.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.AssertionsForClassTypes.not;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest

@AutoConfigureTestDatabase(replace = NONE)

public class RepositoriesTest {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TransactionRepository transactionRepository;
    @Test
    public void existLoans(){

        List<Loan> loans = loanRepository.findAll();

        assertThat(loans,is(not(empty())));

    }

    ///verifica que existan préstamos en la base de datos, primero busca todos los préstamos y luego verificar que la lista no esté vacía.
    @Test
    //verifica que en la lista de los préstamos exista uno llamado “Personal”.
    public void existPersonalLoan(){

        List<Loan> loans = loanRepository.findAll();

        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));

    }

    @Test
    //verifica que en la lista de los préstamos exista uno llamado “Vehicle”.
    public void existVehicleLoan(){

        List<Loan> loans = loanRepository.findAll();

        assertThat(loans, hasItem(hasProperty("name", is("Vehicle"))));

    }
    @Test
    //verifica que en la lista de los préstamos exista uno llamado “Hipotecario”.
    public void existHipotecaryLoan(){

        List<Loan> loans = loanRepository.findAll();

        assertThat(loans, hasItem(hasProperty("name", is("Hipotecary"))));

    }
    @Test
    //verificar que existen clientes en el repositorio
    public void existClients(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients,is(not(empty())));
    }
    //verificar que existen cuentas en el repositorio
    @Test
    public void existAccounts(){
        List<Account> accounts = accountRepository.findAll();
            assertThat(accounts,is(not(empty())));
    }

    //verificar que existen cards en el repositorio
    @Test
    public void existCards(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards,is(not(empty())));
    }
    ///  verificar que exista una tarjeta de tipo credito en la db
    @Test
    public void existCreditCards(){
        List<Card> cards = cardRepository.findAll();

        assertThat(cards, hasItem(hasProperty("name", is("Credit"))));
    }

    ///  verificar que exista una tarjeta de tipo débito en la db
    @Test
    public void existDebitCards(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards, hasItem(hasProperty("name", is("Credit"))));
    }
    @Test
    public void existTransactions(){
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions,is(not(empty())));
    }
}



