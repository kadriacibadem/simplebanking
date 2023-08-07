package com.eteration.simplebanking.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

// This class is a place holder you can change the complete implementation
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Date date;
    private double amount;


    private String approvalCode;


    @ManyToOne
    @JoinColumn(name = "accountNumber")
    @JsonBackReference
    private Account account;

    public Transaction(double amount) {
    	this.amount = amount;
    	this.date = new Date();
    }

    @Override
    public String toString() {
    	        return "Transaction{" +
                ", date=" + date +
                ", amount=" + amount +
                ", type=" + this.getClass().getTypeName() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, amount);
    }
}
