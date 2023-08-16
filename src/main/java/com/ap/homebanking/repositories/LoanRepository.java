package com.ap.homebanking.repositories;
import com.ap.homebanking.models.Loan;
import com.ap.homebanking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
