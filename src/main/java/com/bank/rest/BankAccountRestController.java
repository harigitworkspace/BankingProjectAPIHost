package com.bank.rest;

import com.bank.binding.BankAccount;
import com.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class BankAccountRestController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/{id}")
    public BankAccount getBankAccountById(@PathVariable Long id) {
        return bankAccountService.getBankAccountById(id);
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @PostMapping
    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.saveBankAccount(bankAccount);
    }

    @PutMapping("/{id}")
    public BankAccount updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        bankAccount.setId(id); // Ensure ID is set from path variable
        return bankAccountService.saveBankAccount(bankAccount);
    }

    @DeleteMapping("/{id}")
    public void deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
    }
}
