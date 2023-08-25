
package com.ap.homebanking.repositories;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource

public interface AccountRepository extends JpaRepository <Account, Long> {

    List<Transaction> findByClient_Id(Long clientId);
    List<Account> findByClient(Client client);
}
