package com.bank.service;

import com.bank.binding.Passbook;

import java.util.Date;
import java.util.List;

public interface PassbookService {
    void recordTransaction(Long accountId, Date transactionDate, String transactionType, Double amount, Double balanceAfterTransaction);
    List<Passbook> getPassbookEntries(Long accountId);
}
