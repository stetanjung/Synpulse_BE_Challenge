package com.synpulse.ebanking.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionKafkaMsg {
    private String transactionId;
    private Long amount;
    private String currency;
    private String type;
    private String accountIBAN;
    private String date;
    private String description;

    public String toString() {
        Gson gson = new GsonBuilder().setDateFormat("YYYY-MM-dd").create();
        return gson.toJson(this);
    }
}
