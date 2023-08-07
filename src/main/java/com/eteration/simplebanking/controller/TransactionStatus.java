package com.eteration.simplebanking.controller;


// This class is a place holder you can change the complete implementation

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;


// Transaction işlemlerinde response olarak döndürülecek olan nesne
public class TransactionStatus {

    private String status;
    private String approvalCode;

    public TransactionStatus() {
        this.status = "OK";
        this.approvalCode = UUID.randomUUID().toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
