package com.synpulse.ebanking.dao;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatement {
    private int totalDebit;
    private int totalCredit;
    private String currency;
    private List<TransactionHistory> transactionHistories;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionHistory {
        private String transactionId;
        private Long transactionAmount;
        private String transactionCurrency;
        private String transactionType;
        private Long conversionAmount;
        private String conversionCurrency;
        private String accountIban;
        private String description;
        private Date transactionDate;
    }
}
