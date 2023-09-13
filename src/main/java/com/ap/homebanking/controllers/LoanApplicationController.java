package com.ap.homebanking.controllers;
import com.ap.homebanking.dtos.LoanApplicationDTO;
import com.ap.homebanking.dtos.LoanDTO;
import com.ap.homebanking.models.*;
import com.ap.homebanking.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping("/api")
public class LoanApplicationController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private LoanService loanService;


    @Autowired
    private ClientLoanService clientLoanService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/loans")
    public List<LoanDTO> getLoans() {
        return loanService.getLoans();
//                loanRepository.findAll().stream().map(loan -> new LoanDTO(loan)).collect(Collectors.toList());
    }

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> createLoan(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication) {
        String email = authentication.getName();
        Client client = clientService.findByEmail(email);


        if (loanApplicationDTO.getMaxAmount() <= 0 || loanApplicationDTO.getPayments() <= 0) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
        }

        // Verificar que el préstamo exista
        Loan loan = loanService.findById(loanApplicationDTO.getLoanId());
        if (loan == null) {
            return new ResponseEntity<>("the loan doesn't exist", HttpStatus.FORBIDDEN);
        }


        if (loanApplicationDTO.getMaxAmount() > loan.getMaxAmount()) {
            return new ResponseEntity<>("Try with a less amount", HttpStatus.BAD_REQUEST);
        }


        Account accountToNumber = accountService.findByNumber(loanApplicationDTO.getToAccountNumber());
        if (accountToNumber == null) {
            return new ResponseEntity<>("the account doesn't exist", HttpStatus.FORBIDDEN);
        }


        if (!client.getAccounts().contains(accountToNumber)) {
            return new ResponseEntity<>("Authentications problems, you need login to continue", HttpStatus.UNAUTHORIZED);
        }

        // Crear una solicitud de préstamo con el monto solicitado más el 20%
        ClientLoan newLoan = new ClientLoan();
        newLoan.setClient(client);
        newLoan.setLoan(loan);
        newLoan.setNameLoan(loan.getName());
        newLoan.setPayment(loanApplicationDTO.getPayments());
        newLoan.setAmount(loanApplicationDTO.getMaxAmount() * 1.2); // 20%

        clientLoanService.saveClientLoan(newLoan);


        Transaction transaction = new Transaction();
        transaction.setAccount(accountToNumber);
        transaction.setAmount(loanApplicationDTO.getMaxAmount());
        transaction.setType(TransactionType.CREDIT);
        transaction.setDescription(loan.getName() + " loan approved");

        transactionService.savedTransactions(transaction);


        accountToNumber.setBalance(accountToNumber.getBalance() + loanApplicationDTO.getMaxAmount());
        accountService.saveAccounts(accountToNumber);

        return new ResponseEntity<>("Loan created", HttpStatus.CREATED);
    }
}
