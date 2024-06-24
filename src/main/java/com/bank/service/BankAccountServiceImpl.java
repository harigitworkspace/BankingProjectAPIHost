package com.bank.service;

import com.bank.binding.BankAccount;
import com.bank.repo.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount getBankAccountById(Long id) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(id);
        return optionalBankAccount.orElse(null);
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public void deleteBankAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }
}
