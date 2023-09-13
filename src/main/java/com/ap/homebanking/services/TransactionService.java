package com.ap.homebanking.services;
import com.ap.homebanking.dtos.TransactionDTO;
import com.ap.homebanking.models.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> transactions(Long Id);

    Transaction savedTransactions(Transaction transaction);

    List<TransactionDTO> getTransactions();
}
