package com.eteration.simplebanking.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class PhoneBillPaymentTransaction extends BillPaymentTransaction{
    private String phoneNumber;
    public PhoneBillPaymentTransaction(String company, String phoneNumber, double payee) {
        super(company,payee);
        this.phoneNumber=phoneNumber;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString(){
    	return "Phone Bill Payment: " + this.getCompany() + " " + this.getPhoneNumber() + " Date " + this.getDate();
    }
}
