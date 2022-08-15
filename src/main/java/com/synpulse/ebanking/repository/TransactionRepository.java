package com.synpulse.ebanking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.synpulse.ebanking.dao.TransactionSql;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionSql, Integer> {

    @Query(value = "SELECT * FROM transaction_records tr WHERE tr.account_iban IN ?1 ORDER BY tr.id LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<TransactionSql> findByAccountIban(List<String> accountIban, int limit, int offset);
}
