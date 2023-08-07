package com.eteration.simplebanking.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// This class is a place holder you can change the complete implementation
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private double balance;

    private String owner;

    @CreatedDate

    private Date createdDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Transaction> transactions;

    public Account(String owner, String accountNumber) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0;
        this.createdDate = new Date();
        this.transactions = new HashSet<>();
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        if(transaction instanceof DepositTransaction) {
            this.credit(transaction.getAmount());
        } else if(transaction instanceof WithdrawalTransaction) {
            this.debit(transaction.getAmount());
        }
        transaction.setAccount(this);
    }

    public void credit(double amount) {
        this.balance += amount;
        transactions.add(new DepositTransaction(amount));
    }

    public void debit(double amount) throws InsufficientBalanceException {
        if(this.balance < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }else{
            this.balance -= amount;
            transactions.add(new WithdrawalTransaction(amount));
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", owner='" + owner + '\'' +
                ", createdDate='" + createdDate + "'" +
                ", transactions=" + transactions +
                '}';
    }

}
