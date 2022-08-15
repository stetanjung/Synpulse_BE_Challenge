package com.synpulse.ebanking.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Slf4j
@Service
public class ExchangeRateService {

    @Value("${exchange.url}")
    private String exchangeUrl;

    private String CURRENCY_CODE = "HKD";

    OkHttpClient client = new OkHttpClient();

    public int getExchangeCurrency(String baseCurrency, String amount) throws IOException {
        HttpUrl httpUrl = new HttpUrl.Builder()
            .scheme("https")
            .host(exchangeUrl)
            .addPathSegment("latest")
            .addQueryParameter("base", baseCurrency)
            .addQueryParameter("amount", amount)
            .addQueryParameter("symbols", CURRENCY_CODE)
            .build();

        Request request = new Request.Builder()
            .url(httpUrl)
            .build();

        String response = client.newCall(request).execute().body().string();
        log.info("[ExchangeRates] request: {} | response: {}", httpUrl.toString(), response);

        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        JsonObject rates = jsonResponse.get("rates").getAsJsonObject();

        return rates.get(CURRENCY_CODE).getAsInt();
    }
}
