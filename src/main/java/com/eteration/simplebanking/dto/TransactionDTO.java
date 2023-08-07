package com.eteration.simplebanking.dto;

import lombok.Data;

import java.util.Date;

// Account içerisindeki transaction listesini dışarıya aktarmak için kullanılan nesne
@Data
public class TransactionDTO {
    private Date date;
    private double amount;
    private String type;
    private String approvalCode;
}
