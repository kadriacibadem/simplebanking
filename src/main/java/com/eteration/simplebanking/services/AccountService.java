package com.eteration.simplebanking.services;


import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

// This class is a place holder you can change the complete implementation
@Service
public class AccountService {
   private AccountRepository accountRepository;
   private TransactionRepository transactionRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    // Kullanıcının hesabına para yatırma işlemini gerçekleştiren metod
    // Db'den kullanıcıyı çeker yeni bir transaction oluşturur ve transaction'ı kaydeder
    // Daha sonra hesabı günceller ve hesabı kaydeder
    public TransactionStatus credit(String accountNumber, double amount) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        Transaction transaction = new DepositTransaction(amount);
        TransactionStatus transactionStatus = new TransactionStatus();
        if(account != null){
            account.setBalance(account.getBalance() + amount);
            transaction.setAccount(account);
            transaction.setApprovalCode(transactionStatus.getApprovalCode());
            accountRepository.save(account);
            transactionRepository.save(transaction);
        }else{
            throw new RuntimeException("Account not found");
        }
        return transactionStatus;
    }

    // Kullanıcının hesabından para çekme işlemini gerçekleştiren metod
    // Db'den kullanıcıyı çeker yeni bir transaction oluşturur ve transaction'ı kaydeder
    // Daha sonra hesabı günceller ve hesabı kaydeder
    public TransactionStatus debit(String accountNumber, double amount) throws InsufficientBalanceException {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        Transaction transaction = new WithdrawalTransaction(amount);
        TransactionStatus transactionStatus = new TransactionStatus();
        if(account != null ){
            if(account.getBalance() < amount){
                throw new InsufficientBalanceException("Insufficient balance");
            }else{
                account.setBalance(account.getBalance() - amount);
                account.getTransactions().add(transaction);
                transaction.setAccount(account);
                transaction.setApprovalCode(transactionStatus.getApprovalCode());
                transactionRepository.save(transaction);
                accountRepository.save(account);
            }
        }else{
            throw new RuntimeException("Account not found");
        }
        return transactionStatus;
    }

    // Account entity'sini AccountDTO'ya çeviren metod
    // AccountDTO'ya çevirirken Transaction entity'sini TransactionDTO'ya çevirir
    public AccountDTO findAccount(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setOwner(account.getOwner());
        accountDTO.setCreationDate(account.getCreatedDate());

        Set<TransactionDTO> transactionDTOSet = new HashSet<>();

        for(Transaction transaction : account.getTransactions()){
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setAmount(transaction.getAmount());
            transactionDTO.setDate(transaction.getDate());
            transactionDTO.setType(transaction.getClass().getSimpleName());
            transactionDTO.setApprovalCode(transaction.getApprovalCode());
            transactionDTOSet.add(transactionDTO);
        }

        accountDTO.setTransactions(transactionDTOSet);

        return accountDTO;
    }


}
