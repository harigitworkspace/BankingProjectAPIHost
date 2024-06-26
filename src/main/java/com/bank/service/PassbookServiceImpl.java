package com.bank.service;

import com.bank.binding.Passbook;
import com.bank.repo.PassbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PassbookServiceImpl implements PassbookService {

    @Autowired
    private PassbookRepository passbookRepository;

    @Override
    public void recordTransaction(Long accountId, Date transactionDate, String transactionType, Double amount, Double balanceAfterTransaction) {
        Passbook passbook = new Passbook(accountId, transactionDate, transactionType, amount, balanceAfterTransaction);
        passbookRepository.save(passbook);
    }

    @Override
    public List<Passbook> getPassbookEntries(Long accountId) {
        return passbookRepository.findByAccountId(accountId);
    }
}
