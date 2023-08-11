package com.ap.homebanking;
import com.ap.homebanking.models.TransactionType;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.repositories.ClientRepository;
import com.ap.homebanking.repositories.TransactionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ap.homebanking.repositories")
@EntityScan(basePackages = "com.ap.homebanking.models")
public class HomebankingApplication {

		public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HomebankingApplication.class, args);


		ClientRepository clientRepository = context.getBean(ClientRepository.class);
		AccountRepository accountRepository = context.getBean(AccountRepository.class);
		TransactionRepository transactionRepository = context.getBean(TransactionRepository.class);

		Client melba = new Client("Melba", "Morel", "melba@minhub.com");
		clientRepository.save(melba);

		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);

		Account account1 = new Account("VIN001", 5000, melba, LocalDate.now());
		Account account2 = new Account("VIN002", 7500, melba, LocalDate.now());
		accountRepository.save(account1);
		accountRepository.save(account2);

		Client anotherClient = new Client("Name", "lastname", "D@example.com");
		clientRepository.save(anotherClient);

		Account anotherAccount1 = new Account("VIN003", 10000, anotherClient, LocalDate.now());
		Account anotherAccount2 = new Account("VIN004", 20000, anotherClient,LocalDate.now());
		accountRepository.save(anotherAccount1);
		accountRepository.save(anotherAccount2);

		//Transacciones

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
	}
}
