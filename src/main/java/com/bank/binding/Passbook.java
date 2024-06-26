package com.bank.binding;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Passbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private Date transactionDate;
    private String transactionType; // DEPOSIT, WITHDRAWAL, TRANSFER
    private Double amount;
    private Double balanceAfterTransaction;

    // Constructors, getters and setters
    public Passbook() {}

    public Passbook(Long accountId, Date transactionDate, String transactionType, Double amount, Double balanceAfterTransaction) {
        this.accountId = accountId;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }
}
