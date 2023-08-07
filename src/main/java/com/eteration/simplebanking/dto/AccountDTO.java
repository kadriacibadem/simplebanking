package com.eteration.simplebanking.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

// Response olarak dönülecek olan nesne
@Data
public class AccountDTO {
    private String accountNumber;
    private String owner;
    private double balance;
    private Date creationDate;
    private Set<TransactionDTO> transactions;
}
