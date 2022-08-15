package com.synpulse.ebanking.dao;

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
@Table(name = "user_account")
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountSql {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user")
    private String userAccount;
    @Column(name = "account_iban")
    private String accountIban;
    @Column(name = "account_currency")
    private String accountCurrency;
}
