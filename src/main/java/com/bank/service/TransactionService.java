package com.bank.service;

import com.bank.binding.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    Transaction deposit(Long accountId, Double amount);
    Transaction withdraw(Long accountId, Double amount);
    Transaction transfer(Long fromAccountId, Long toAccountId, Double amount);
    List<Transaction> getTransactionsByAccountId(Long accountId);
    List<Map<String, Object>> summarizeTransactionsByAccount();
}
