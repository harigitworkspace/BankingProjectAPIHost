package com.bank.service;

import com.bank.binding.Account;
import com.bank.binding.Transaction;
import com.bank.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public Transaction deposit(Long accountId, Double amount) {
        Account account = accountService.updateBalance(accountId, amount);
        if (account != null) {
            Transaction transaction = new Transaction();
            transaction.setAccountId(accountId);
            transaction.setAmount(amount);
            transaction.setTransactionDate(new Date());
            transaction.setTransactionType("DEPOSIT");
            return transactionRepository.save(transaction);
        }
        return null;
    }

    @Override
    public Transaction withdraw(Long accountId, Double amount) {
        Account account = accountService.getAccountById(accountId);
        if (account != null && account.getBalance() >= amount) {
            accountService.updateBalance(accountId, -amount);
            Transaction transaction = new Transaction();
            transaction.setAccountId(accountId);
            transaction.setAmount(amount);
            transaction.setTransactionDate(new Date());
            transaction.setTransactionType("WITHDRAWAL");
            return transactionRepository.save(transaction);
        }
        return null;
    }

    @Override
    public Transaction transfer(Long fromAccountId, Long toAccountId, Double amount) {
        Account fromAccount = accountService.getAccountById(fromAccountId);
        Account toAccount = accountService.getAccountById(toAccountId);
        if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount) {
            accountService.updateBalance(fromAccountId, -amount);
            accountService.updateBalance(toAccountId, amount);
            Transaction transaction = new Transaction();
            transaction.setAccountId(fromAccountId);
            transaction.setToAccountId(toAccountId);
            transaction.setAmount(amount);
            transaction.setTransactionDate(new Date());
            transaction.setTransactionType("TRANSFER");
            return transactionRepository.save(transaction);
        }
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
