package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class BillPaymentTransaction extends WithdrawalTransaction{

    private int payee;
    private String company;
    public BillPaymentTransaction(String company,double payee) {
        super(payee);
        this.company=company;
    }

    public int getPayee() {
        return payee;
    }

    public void setPayee(int payee) {
        this.payee = payee;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company=company;
    }


    @Override
    public String toString() {
        return "Bill Payment: " + this.company + " " + this.payee + " Date " + this.getDate();
    }
}
