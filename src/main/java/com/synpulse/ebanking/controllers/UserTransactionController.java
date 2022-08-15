package com.synpulse.ebanking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synpulse.ebanking.dao.TransactionStatement;
import com.synpulse.ebanking.services.TransactionStatementService;

@RestController
@RequestMapping("/user")
public class UserTransactionController {

    @Autowired
    private TransactionStatementService transactionStatementService;

    @GetMapping("/statement")
    public TransactionStatement getStatement(@RequestParam int limit, @RequestParam int page,
            @RequestHeader(name = "Authorization") String token) {
        return transactionStatementService.getStatement(token, limit, page);
    }

}
