package com.ap.homebanking.repositories;
import com.ap.homebanking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource

public interface TransactionRepository extends JpaRepository <Transaction, Long>{
    List<Transaction> findByAccountId(long accountId);
}
