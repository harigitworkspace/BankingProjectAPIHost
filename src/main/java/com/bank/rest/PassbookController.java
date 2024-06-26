package com.bank.rest;


import com.bank.binding.Passbook;
import com.bank.service.PassbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PassbookController {
    @Autowired
    private PassbookService passbookService;

    @GetMapping("/passbook/{accountId}")
    public List<Passbook> getPassbookEntries(@PathVariable String accountId) {
    	   Long accountIdLong = Long.parseLong(accountId);
           return passbookService.getPassbookEntries(accountIdLong);
    }
}
