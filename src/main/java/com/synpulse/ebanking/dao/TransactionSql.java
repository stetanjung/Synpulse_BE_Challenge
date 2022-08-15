package com.synpulse.ebanking.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "transaction_records")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSql {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "transaction_amount")
    private Long transactionAmount;
    @Column(name = "transaction_currency")
    private String transactionCurrency;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "account_iban")
    private String accountIban;
    @Column(name = "description")
    private String description;
    @Column(name = "transaction_date")
    private Date transactionDate;
}
