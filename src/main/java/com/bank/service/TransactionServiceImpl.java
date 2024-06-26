package com.bank.service;

import com.bank.binding.Account;
import com.bank.binding.Transaction;
import com.bank.exception.AccountNotFoundException;
import com.bank.exception.InsufficientFundsException;
import com.bank.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PassbookService passbookService;

    @Override
    public Transaction deposit(Long accountId, Double amount) {
        Account account = accountService.updateBalance(accountId, amount);
        if (account != null) {
            Transaction transaction = new Transaction();
            transaction.setAccountId(accountId);
            transaction.setAmount(amount);
            transaction.setTransactionDate(new Date());
            transaction.setTransactionType("DEPOSIT");
            Transaction savedTransaction = transactionRepository.save(transaction);

            passbookService.recordTransaction(accountId, transaction.getTransactionDate(), transaction.getTransactionType(), transaction.getAmount(), account.getBalance());

            return savedTransaction;
        }
        else {
                  throw new AccountNotFoundException("Account ID " + accountId + " not found.");
              
        }
    }

    @Override
    public Transaction withdraw(Long accountId, Double amount) {
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            throw new AccountNotFoundException("Account ID " + accountId + " not found.");
        }
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient fund in account ID " + accountId);
        }
        accountService.updateBalance(accountId, -amount);
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType("WITHDRAWAL");
        passbookService.recordTransaction(accountId, transaction.getTransactionDate(), transaction.getTransactionType(), -transaction.getAmount(), account.getBalance());

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction transfer(Long fromAccountId, Long toAccountId, Double amount) {
        Account fromAccount = accountService.getAccountById(fromAccountId);
        Account toAccount = accountService.getAccountById(toAccountId);
        if (fromAccount == null) {
            throw new AccountNotFoundException("From Account ID " + fromAccountId + " not found.");
        }
        if (toAccount == null) {
            throw new AccountNotFoundException("To Account ID " + toAccountId + " not found.");
        }
        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient fund in account ID " + fromAccountId);
        }
        accountService.updateBalance(fromAccountId, -amount);
        accountService.updateBalance(toAccountId, amount);
        Transaction transaction = new Transaction();
        transaction.setAccountId(fromAccountId);
        transaction.setToAccountId(toAccountId);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType("TRANSFER");
     // Record transaction in passbook for both accounts involved in transfer
        passbookService.recordTransaction(fromAccountId, transaction.getTransactionDate(), transaction.getTransactionType(), -transaction.getAmount(), fromAccount.getBalance());
        passbookService.recordTransaction(toAccountId, transaction.getTransactionDate(), "TRANSFER", transaction.getAmount(), toAccount.getBalance());

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
    
//    @Override
//    public Map<Long, List<Transaction>> summarizeTransactionsByAccount() {
//        List<Transaction> allTransactions = transactionRepository.findAll();
//        return allTransactions.stream()
//                .collect(Collectors.groupingBy(Transaction::getAccountId));
//    }
    
    @Override
    public List<Map<String, Object>> summarizeTransactionsByAccount() {
        List<Transaction> allTransactions = transactionRepository.findAll();
        Map<Long, List<Transaction>> transactionsByAccountId = allTransactions.stream()
                .collect(Collectors.groupingBy(Transaction::getAccountId));

        List<Map<String, Object>> accountSummaries = new ArrayList<>();

        for (Map.Entry<Long, List<Transaction>> entry : transactionsByAccountId.entrySet()) {
            Long accountId = entry.getKey();
            List<Transaction> transactions = entry.getValue();

            Account account = accountService.getAccountById(accountId);
            if (account != null) {
                // Create a map to represent the account summary
                Map<String, Object> summary = Map.of(
                        "accountId", account.getId(),
                        "accountName", account.getAccountName(),
                        "accountNumber", account.getAccountNumber(),
                        "accountType", account.getAccountType(),
                        "balance", account.getBalance(),
                        "transactions", transactions
                );
                accountSummaries.add(summary);
            }
        }

        return accountSummaries;
    }


    private String generateTransactionId() {
        // Logic to generate a unique transaction ID
        return "TXN" + System.currentTimeMillis();
    }
}
