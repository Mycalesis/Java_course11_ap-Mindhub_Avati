package com.ap.homebanking.services.implement;

import com.ap.homebanking.dtos.AccountDTO;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplement implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account saveAccounts(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<AccountDTO> getAccountDTOs() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOs = accounts.stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toList());

        return accountDTOs;
    }

    @Override
    public Account findById(long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return accountRepository.existsByNumber(accountNumber);
    }

    @Override
    public int countByClient(Client client) {
        return accountRepository.countByClient(client);
    }

    @Override
    public Account findByClientAndNumber(Client client, String number) {
        return accountRepository.findByClientAndNumber(client,number);
    }
}
