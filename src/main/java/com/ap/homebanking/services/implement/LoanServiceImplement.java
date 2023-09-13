package com.ap.homebanking.services.implement;

import com.ap.homebanking.dtos.LoanDTO;
import com.ap.homebanking.models.Loan;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.repositories.ClientLoanRepository;
import com.ap.homebanking.repositories.LoanRepository;
import com.ap.homebanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImplement implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Override
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(loan -> new LoanDTO(loan)).collect(Collectors.toList());
    }

    @Override
    public Loan findById(Long loanId){ return loanRepository.findById(loanId).orElse(null);}


}
