package com.bank.service;

import com.bank.binding.Account;
import com.bank.exception.AccountNotFoundException;
import com.bank.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private long accountNumberCounter;

    @PostConstruct
    public void init() {
        accountNumberCounter = accountRepository.findAll()
            .stream()
            .mapToLong(account -> Long.parseLong(account.getAccountNumber()))
            .max()
            .orElse(999999L) + 1;
    }

    @Override
    public Account createAccount(Account account) {
        account.setAccountNumber(String.valueOf(accountNumberCounter++));
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        Optional<Account> existingAccountOpt = accountRepository.findById(id);
        if (existingAccountOpt.isPresent()) {
            Account existingAccount = existingAccountOpt.get();
            existingAccount.setAccountName(account.getAccountName());
            existingAccount.setAccountType(account.getAccountType());
            existingAccount.setBalance(account.getBalance());
            return accountRepository.save(existingAccount);
        }
        return null;
    }

    @Override
    public boolean deleteAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Account updateBalance(Long id, Double amount) {
        Optional<Account> accountOpt = accountRepository.findById(id);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            account.setBalance(account.getBalance() + amount);
            return accountRepository.save(account);
        }
        return null;
    }

//    @Override
//    public Double getBalance(Long id) {
//        Optional<Account> accountOpt = accountRepository.findById(id);
//        return accountOpt.map(Account::getBalance).orElse(null);
//    }
    @Override
    public Double getBalance(Long id) {
        Optional<Account> accountOpt = accountRepository.findById(id);
        Account account = accountOpt.orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " not found"));
        return account.getBalance();
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
