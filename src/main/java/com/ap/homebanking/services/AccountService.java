package com.ap.homebanking.services;

import com.ap.homebanking.dtos.AccountDTO;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;


import java.util.List;

public interface AccountService {
    Account saveAccounts(Account account);

    List<AccountDTO> getAccountDTOs();

    Account findById(long id);

    Account findByNumber(String number);

    boolean existsByAccountNumber(String accountNumber);

    int countByClient(Client client);

    Account findByClientAndNumber(Client client, String number);
}
