package com.ap.homebanking;

import com.ap.homebanking.models.*;
import com.ap.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Bean
	public ApplicationRunner initData(
			ClientRepository clientRepository,
			AccountRepository accountRepository,
			TransactionRepository transactionRepository,
			LoanRepository loanRepository,
			ClientLoanRepository clientLoanRepository, CardRepository cardRepository

			) {
		return args -> {

			///MELBA MOREL
			Client melba = new Client("Melba", "Morel", "melba@mindhub.com",passwordEncoder.encode("melba123"), "CLIENT");
			clientRepository.save(melba);

			LocalDate today = LocalDate.now();
			LocalDate tomorrow = today.plusDays(1);
			//clients acc
			Account account1 = new Account("VIN-00000001", 5000, melba, LocalDate.now());
			Account account2 = new Account("VIN-00000002", 7500, melba, LocalDate.now());
			accountRepository.save(account1);
			accountRepository.save(account2);


			///MELBA'S TRANSACTIONS

			Transaction transaction1 = new Transaction(-2000, "Compra en farmacia null", LocalDate.now(), TransactionType.DEBIT);
			account1.addTransaction(transaction1);
			transactionRepository.save(transaction1); // Guardar la transacción en la base de datos

			Transaction transaction2 = new Transaction(-2000, "Compra en Supermercado Chun li", LocalDate.now(), TransactionType.DEBIT);
			account1.addTransaction(transaction2);
			transactionRepository.save(transaction2);

			Transaction transaction3 = new Transaction(500, "Devolucion en Supermercado Chun li", LocalDate.now(), TransactionType.CREDIT);
			account1.addTransaction(transaction3);
			transactionRepository.save(transaction3);


			Transaction transaction4 = new Transaction(35000, "Devolución en farmacia", LocalDate.now(), TransactionType.CREDIT);
			account2.addTransaction(transaction4);
			transactionRepository.save(transaction4);

			Transaction transaction5 = new Transaction(-250, "Pago en Kiosko Mappi", LocalDate.now(), TransactionType.DEBIT);
			account2.addTransaction(transaction5);
			transactionRepository.save(transaction5);


			//***** lOANS *******///

			// Data Loans
			Loan loan1 = new Loan("Hipotecary", 500000, List.of(12, 24, 36, 48, 60));
			loanRepository.save(loan1);

			Loan loan2 = new Loan("Personal", 100000, List.of(6, 12, 24));
			loanRepository.save(loan2);

			Loan loan3 = new Loan("Vehicle", 300000, List.of(6, 12, 24, 36));
			loanRepository.save(loan3);


			///MELBA'S LOANS

			ClientLoan clientLoan01 = new ClientLoan(loan1, "Hipotecary", 60, 400000, melba);
			clientLoanRepository.save(clientLoan01);

			ClientLoan clientLoan02 = new ClientLoan(loan2, "Personal", 12, 50000, melba);
			clientLoanRepository.save(clientLoan02);


			//cards
			//Melba
			Card card01 = new Card(TransactionType.DEBIT, "Melba Morel",Color.GOLD, "2223-4582-2233-9154", 225,LocalDate.now(),LocalDate.ofYearDay(28,10) );
			melba.addCard(card01);
			cardRepository.save(card01);
			Card card02 = new Card(TransactionType.CREDIT, "Melba Morel",Color.TITANIUM, "7273-4782-2273-9754", 275,LocalDate.now(),LocalDate.ofYearDay(28,10) );
			melba.addCard(card02);
			cardRepository.save(card02);


			////*******************************************************************************///////


			///MERENGADA VAINILLA

			Client anotherClient = new Client("Merengada", "Vainilla", "Mere.Vai@example.com", passwordEncoder.encode("vainillaSky"), "CLIENT");
			clientRepository.save(anotherClient);

			Account anotherAccount1 = new Account("VIN-00000003", 10000, anotherClient, LocalDate.now());
			Account anotherAccount2 = new Account("VIN-00000004", 20000, anotherClient,LocalDate.now());
			accountRepository.save(anotherAccount1);
			accountRepository.save(anotherAccount2);

			//MERENGADA'S TRANSACTIONS

			Transaction transaction01 = new Transaction(300000, "Honorarios", LocalDate.now(), TransactionType.CREDIT);
			anotherAccount1.addTransaction(transaction01);
			transactionRepository.save(transaction01);

			Transaction transaction02 = new Transaction(15000, "arba", LocalDate.now(), TransactionType.DEBIT);
			anotherAccount1.addTransaction(transaction02);
			transactionRepository.save(transaction02);

			Transaction transaction03 = new Transaction(-6000, "EDENOR", LocalDate.now(), TransactionType.DEBIT);
			anotherAccount2.addTransaction(transaction03);
			transactionRepository.save(transaction03);

			Transaction transaction04 = new Transaction(600, "Reintegro", LocalDate.now(), TransactionType.CREDIT);
			anotherAccount2.addTransaction(transaction04);
			transactionRepository.save(transaction04);

			//MERENGADA'S LOANS

			ClientLoan clientLoan03 = new ClientLoan(loan2, "Personal", 24, 100000, anotherClient);
			clientLoanRepository.save(clientLoan03);

			ClientLoan clientLoan04 = new ClientLoan(loan3, "Vehicle", 36, 200000, anotherClient);
			clientLoanRepository.save(clientLoan04);

			//Merengada

			Card card03 = new Card(TransactionType.DEBIT, "Merengada Vainilla",Color.SILVER, "9823-4562-2233-9855", 655,LocalDate.now(),LocalDate.ofYearDay(28,10) );
			anotherClient.addCard(card03);
			cardRepository.save(card03);
				//*********************************ADMIN**************************//
			Client admin = new Client("Pepito", "Banquero", "pepito@mindhub.com", passwordEncoder.encode("123"), "ADMIN");
			clientRepository.save(admin);

	};
}
		}
