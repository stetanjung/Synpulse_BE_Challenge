package com.synpulse.ebanking.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.synpulse.ebanking.dao.TransactionKafkaMsg;
import com.synpulse.ebanking.dao.TransactionSql;
import com.synpulse.ebanking.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionKafkaConsumer {
    @Autowired
    private TransactionRepository transactionRepository;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group.id}")
    public void consumeMessage(String msgString) {
        Gson gson = new Gson();
        TransactionKafkaMsg kafkaMsg = gson.fromJson(msgString, TransactionKafkaMsg.class);

        TransactionSql transactionSql = TransactionSql.builder()
                .transactionId(kafkaMsg.getTransactionId())
                .transactionAmount(kafkaMsg.getAmount())
                .transactionCurrency(kafkaMsg.getCurrency())
                .transactionType(kafkaMsg.getType())
                .accountIban(kafkaMsg.getAccountIBAN())
                .description(kafkaMsg.getDescription())
                .transactionDate(Date.valueOf(kafkaMsg.getDate()))
                .build();

        try {
            TransactionSql insertData = transactionRepository.save(transactionSql);
            log.info("Save data to db: {}", insertData);
        } catch (Exception e) {
            log.error("[consumeTransaction] error: {}", e);
        }
        log.info("Consumed message: {}", msgString);
    }
}
