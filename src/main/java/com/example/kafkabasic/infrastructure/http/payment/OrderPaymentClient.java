package com.example.kafkabasic.infrastructure.http.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class OrderPaymentClient {

    @Qualifier("payClient") private final WebClient webClient;

    public void test() {
        webClient.get()
                .uri("")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }
}
