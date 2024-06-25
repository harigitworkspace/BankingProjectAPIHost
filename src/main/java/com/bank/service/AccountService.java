package com.bank.service;

import com.bank.binding.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    Account getAccountById(Long id);
    Account updateAccount(Long id, Account account);
    boolean deleteAccount(Long id);
    Account updateBalance(Long id, Double amount);
    Double getBalance(Long id);
    List<Account> getAllAccounts();
}
