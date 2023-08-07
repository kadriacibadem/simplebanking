package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping("/account/v1/")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("{accountNumber}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("accountNumber") String accountNumber) {
        AccountDTO account = accountService.findAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    // -------------- Test için -----------------

//    @PostMapping("credit/{accountNumber}")
//    public ResponseEntity<TransactionStatus> credit(@PathVariable("accountNumber")String accountNumber, Transaction transaction) {
//        TransactionStatus transactionStatus = accountService.credit(accountNumber,transaction.getAmount());
//        return ResponseEntity.status(HttpStatus.OK).body(transactionStatus);
//    }
//
//
//    @PostMapping("debit/{accountNumber}")
//    public ResponseEntity<TransactionStatus> debit(@PathVariable("accountNumber")String accountNumber, Transaction transaction) throws InsufficientBalanceException {
//        TransactionStatus transactionStatus = accountService.debit(accountNumber, transaction.getAmount());
//        return ResponseEntity.status(HttpStatus.OK).body(transactionStatus);
//    }

    // -------------- url'den istek için -----------------

    @PostMapping("credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable("accountNumber")String accountNumber, @RequestParam("amount") double amount) {
         TransactionStatus transactionStatus = accountService.credit(accountNumber,amount);
            return ResponseEntity.status(HttpStatus.OK).body(transactionStatus);
    }



        @PostMapping("debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable("accountNumber")String accountNumber, @RequestParam("amount") double amount) throws InsufficientBalanceException {
        TransactionStatus transactionStatus = accountService.debit(accountNumber, amount);
        return ResponseEntity.status(HttpStatus.OK).body(transactionStatus);
    }


}