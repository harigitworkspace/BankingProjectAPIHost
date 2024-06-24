package com.bank.service;

import com.bank.binding.BankAccount;

import java.util.List;

public interface BankAccountService {

    BankAccount getBankAccountById(Long id);

    List<BankAccount> getAllBankAccounts();

    BankAccount saveBankAccount(BankAccount bankAccount);

    void deleteBankAccount(Long id);
}
