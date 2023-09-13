package com.ap.homebanking.services.implement;


import com.ap.homebanking.dtos.TransactionDTO;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.repositories.TransactionRepository;
import com.ap.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImplement implements TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public List<Transaction> transactions(Long id) {
        return transactionRepository.findByAccountId(id);
    }

    @Override
    public Transaction savedTransactions(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDTO> getTransactions() {
        return transactionRepository.findAll().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toList());
    }

}
