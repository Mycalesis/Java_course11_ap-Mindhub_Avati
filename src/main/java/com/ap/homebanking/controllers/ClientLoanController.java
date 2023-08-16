package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.ClientLoanDTO;
import com.ap.homebanking.repositories.ClientLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;



    @RestController
    @RequestMapping("/api")
    public class ClientLoanController {

        private ClientLoanRepository clientLoanRepository;

        @Autowired
        public ClientLoanController (ClientLoanRepository clientloanRepository){
            this.clientLoanRepository = clientLoanRepository;
        }

        @GetMapping("/loans")
        public List<ClientLoanDTO> getClientLoan() {
            return clientLoanRepository.findAll().stream().map(clientLoan -> new ClientLoanDTO(clientLoan)).collect(Collectors.toList());
        }
    }

