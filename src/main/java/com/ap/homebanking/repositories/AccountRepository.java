
package com.ap.homebanking.repositories;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource

public interface AccountRepository extends JpaRepository <Account, Long> {
    int countByClient(Client client);


    Account findByClientAndNumber(Client client, String number);

    Account findByNumber(String Number);

    boolean existsByNumber(String accountNumber);
}
