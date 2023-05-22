package com.example.kafkabasic.application;

public record OrderPaymentEvent(Long orderId, int price) {
}
