package com.synpulse.ebanking.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synpulse.ebanking.dao.TransactionSql;
import com.synpulse.ebanking.dao.TransactionStatement;
import com.synpulse.ebanking.dao.UserAccountSql;
import com.synpulse.ebanking.repository.TransactionRepository;
import com.synpulse.ebanking.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionStatementService {
    @Autowired
    private TokenVerifierService tokenVerifierService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ExchangeRateService exchangeRateService;


    public TransactionStatement getStatement(String token, int limit, int page) {
        String user = tokenVerifierService.getUser(token);
        List<UserAccountSql> userAccounts = userRepository.findByUserAccount(user);
        List<String> userIbans= new ArrayList<String>();

        for (UserAccountSql userAccount : userAccounts) {
            userIbans.add(userAccount.getAccountIban());
        }

        int totalCredit = 0;
        int totalDebit = 0;
        List<TransactionSql> transactions = transactionRepository.findByAccountIban(userIbans, limit, (page-1) * limit);
        List<TransactionStatement.TransactionHistory> transactionHistories = new ArrayList<>();
        for (TransactionSql transaction : transactions) {
            if ("credit".equals(transaction.getTransactionType())) {
                totalCredit += exchangeRates(transaction.getTransactionCurrency(), transaction.getTransactionAmount());
            } else if ("debit".equals(transaction.getTransactionType())) {
                totalDebit += exchangeRates(transaction.getTransactionCurrency(), transaction.getTransactionAmount());
            }

            TransactionStatement.TransactionHistory transactionHistory = TransactionStatement.TransactionHistory.builder()
                    .transactionId(transaction.getTransactionId())
                    .transactionAmount(transaction.getTransactionAmount())
                    .transactionCurrency(transaction.getTransactionCurrency())
                    .transactionType(transaction.getTransactionType())
                    .accountIban(transaction.getAccountIban())
                    .description(transaction.getDescription())
                    .transactionDate(transaction.getTransactionDate())
                    .build();
            transactionHistories.add(transactionHistory);
        }

        return TransactionStatement.builder()
            .totalCredit(totalCredit)
            .totalDebit(totalDebit)
            .transactionHistories(transactionHistories)
            .build();
    }

    private int exchangeRates(String currency, Long amount) {
        int conversionAmount = 0;
        try {
            return exchangeRateService.getExchangeCurrency(currency, String.valueOf(amount));
        } catch (IOException e) {
            log.error("Exchange Currency Error: {}", e);
        }
        return conversionAmount;
    }
}
