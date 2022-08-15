package com.synpulse.ebanking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synpulse.ebanking.dao.TransactionKafkaMsg;

@RestController
@RequestMapping("/transaction")
public class ProduceTransactionController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    @PostMapping("/produce")
    public String produceTransMsg(@RequestBody TransactionKafkaMsg transaction) {

        kafkaTemplate.send(kafkaTopic, transaction.toString());

        return "message produced " + transaction.toString();
    }
}
