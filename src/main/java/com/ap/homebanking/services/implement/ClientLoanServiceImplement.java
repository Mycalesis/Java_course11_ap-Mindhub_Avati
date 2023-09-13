package com.ap.homebanking.services.implement;

import com.ap.homebanking.models.ClientLoan;
import com.ap.homebanking.repositories.ClientLoanRepository;
import com.ap.homebanking.services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientLoanServiceImplement implements ClientLoanService {
    @Autowired
    ClientLoanRepository clientLoanRepository;

    @Override
    public ClientLoan saveClientLoan(ClientLoan newLoan){
        return clientLoanRepository.save(newLoan);
    }





}
