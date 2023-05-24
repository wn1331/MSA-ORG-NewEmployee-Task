package com.example.paymentapi.infrastructure.kafka.event;

public record PaymentConsumerEvent(Long orderId, int price) {
}