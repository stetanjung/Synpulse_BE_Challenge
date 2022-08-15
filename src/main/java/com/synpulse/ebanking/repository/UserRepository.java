package com.synpulse.ebanking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synpulse.ebanking.dao.UserAccountSql;

@Repository
public interface UserRepository extends JpaRepository<UserAccountSql, Integer> {
    
    List<UserAccountSql> findByUserAccount(String userAccount);
}
